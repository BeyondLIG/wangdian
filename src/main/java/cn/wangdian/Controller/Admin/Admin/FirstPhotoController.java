package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.FirstPhoto;
import cn.wangdian.Service.FirstPhotoService;
import cn.wangdian.utils.ExecuteResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by 25065 on 2016/9/18.
 */
@Controller
@RequestMapping("/admin")
public class FirstPhotoController {

    private Log logger= LogFactory.getLog(FirstPhotoController.class);

    private ExecuteResult executeResult=new ExecuteResult();

    @Autowired
    private FirstPhotoService firstPhotoService;

    private static int parameterCountBefore=0;

    @RequestMapping("/firstPhoto/list")
    public String list(Model model,
                       String orderField,String orderDirection,Integer pageSize,Integer pageCurrent,
                       String name,String inTime){

        Page<FirstPhoto> firstPhotoList= null;
        try {
            int parameterCountNow=0;
            if (name!=null&&!name.equals("")){
                model.addAttribute("name",name);
                parameterCountNow++;
            }
            if (inTime!=null&&!inTime.equals("")){
                model.addAttribute("inTime",inTime);
                parameterCountNow++;
            }


            if (pageSize==null||pageSize.equals("")){
                pageSize=5;
            }

            //有多少页
            int count=firstPhotoService.countAllByIsDel0(name,inTime);
            int pageNumbers=0;
            if (count%pageSize==0){
                //整除
                pageNumbers=count/pageSize;
            }else {
                //有余数
                pageNumbers=count/pageSize+1;
            }

            if (pageCurrent==null||pageCurrent.equals("")){
                pageCurrent=0;
            }else if (parameterCountNow!=parameterCountBefore){
                pageCurrent=0;
                parameterCountBefore=parameterCountNow;
            }else if (pageCurrent>pageNumbers){
                pageCurrent=0;
            }else {
                pageCurrent=pageCurrent-1;
            }

            PageRequest pageRequest=new PageRequest(pageCurrent,pageSize);
            firstPhotoList = firstPhotoService.findAllByIsDel0(name,inTime,orderField,orderDirection,pageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("firstPhotoList",firstPhotoList);
        return "backPage/admin/firstPhoto/firstPhotoList";
    }

    @RequestMapping(value = "/firstPhoto/add",method = RequestMethod.GET)
    public String adminAdd(Integer id,Model model){
        if (id!=null){
            FirstPhoto firstPhoto=null;
            try {
                firstPhoto=firstPhotoService.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("firstPhoto",firstPhoto);
        }
        return "backPage/admin/firstPhoto/add";
    }

    @ResponseBody
    @RequestMapping(value = "/firstPhoto/add",method = RequestMethod.POST)
    public Object adminAdd(FirstPhoto firstPhoto,String advertInTime){
        try {

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            firstPhoto.setInTime(sdf.parse(advertInTime));
            if (firstPhoto.getId()!=null){
                //编辑
                firstPhotoService.update(firstPhoto);
            }else {
                //添加
                firstPhotoService.save(firstPhoto);
            }
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }

    /**
     * 图片上传
     */
    @ResponseBody
    @RequestMapping("/firstPhoto/upload")
    public Object uploadPhoto( HttpServletRequest request, HttpServletResponse response) throws Exception{


        String basePath=request.getSession().getServletContext().getRealPath("/");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHH");
        String ymd="uploads"+"/"+sdf.format(new Date());
        String parentPath=basePath+ymd+"/";

        String readFilePath=null;

        String path=null;

        try {
            //解析器解析request的上下文
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

            String fileName=null;
            //先判断request中是否包含multipart类型的数据，
            if (multipartResolver.isMultipart(request)){
                //再将request中的数据转化成multipart类型的数据
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) request;
                Iterator iterator=multiRequest.getFileNames();
                while (iterator.hasNext()){
                    MultipartFile file=multiRequest.getFile((String)iterator.next());
                    if (file!=null){
                        fileName=file.getOriginalFilename();

                        String imageType=fileName.substring(fileName.lastIndexOf(".")).trim().toLowerCase();
                        String uuid= UUID.randomUUID().toString().replace("-","");//返回一个随机UUID
                        String newFileName=uuid+imageType;
//                        String fileNameFirst=fileName.substring(0,fileName.lastIndexOf(".")).trim().toLowerCase();
//                        String hashStr=Integer.toHexString(fileNameFirst.hashCode());
//                        String newFileName=hashStr+imageType;

                        path=parentPath+newFileName;
                        readFilePath=ymd+"/"+newFileName;

                        File localFile=new File(path);
                        if (!localFile.getParentFile().exists()){
                            localFile.getParentFile().mkdirs();
                        }
                        file.transferTo(localFile);
                        System.out.println(readFilePath);
                    }
                }
            }
            return executeResult.jsonReturnFile(200,fileName,readFilePath);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/firstPhoto/delete")
    public Object delete(Integer id){
        try {
            firstPhotoService.deleteByPrimaryKey(id);
            return executeResult.jsonReturn(200,false);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage(),false);
        }
    }
}
