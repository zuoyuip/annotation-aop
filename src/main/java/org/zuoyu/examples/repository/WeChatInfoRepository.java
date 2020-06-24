package org.zuoyu.examples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.zuoyu.examples.model.WeChatInfo;

/**
 * @date 2020/6/24
 * @time 下午2:03
 * @description 数据仓库.
 * @author zuoyu
 */
interface WeChatInfoRepository extends JpaRepository<WeChatInfo, Integer>,
    JpaSpecificationExecutor<WeChatInfo> {

}
