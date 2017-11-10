package cn.wangdian.Repository;

import cn.wangdian.Model.FirstPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 25065 on 2016/9/19.
 */
public interface FirstPhotoRepository extends JpaRepository<FirstPhoto,String>,JpaSpecificationExecutor<FirstPhoto> {

    @Query("select o from FirstPhoto o where o.id=:id ")
    public FirstPhoto findById(@Param("id") Integer id);

    @Modifying
    @Query("update FirstPhoto o set o.isDel=1 where o.id=:id ")
    public void deleteByPrimaryKey(@Param("id") Integer id);
}
