package org.zuoyu.examples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zuoyu.examples.model.WeChatInfo;

/**
 * @date 2020/6/24
 * @time 下午2:03
 * @description 数据仓库.
 * @author zuoyu
 */
@Repository
public interface WeChatInfoRepository
    extends JpaRepository<WeChatInfo, Integer>, JpaSpecificationExecutor<WeChatInfo> {

  /**
   * 删除
   *
   * @param weChatInfoId - ID
   */
  @Modifying
  @Query("UPDATE WeChatInfo w SET w.deleted = true WHERE w.weChatInfoId = :weChatInfoId")
  void makeDeleted(@Param("weChatInfoId") Integer weChatInfoId);

  /**
   * 根据Id修改城市
   *
   * @param city - 城市名称
   * @param weChatInfoId - ID
   */
  @Modifying
  @Query("UPDATE WeChatInfo w set w.city = :city WHERE w.weChatInfoId = :weChatInfoId")
  void updateCityById(@Param("city") String city, @Param("weChatInfoId") Integer weChatInfoId);
}
