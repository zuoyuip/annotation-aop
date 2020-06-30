package org.zuoyu.examples.service;

import java.util.List;
import org.zuoyu.examples.model.WeChatInfo;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 上午9:43
 * @description 微信账户服务.
 */
public interface IWeChatInfoService {

  /** 添加 */
  WeChatInfo save(WeChatInfo weChatInfo);

  /** 删除 */
  void deleteById(Integer weChatId);

  /** 修改 */
  WeChatInfo updateById(WeChatInfo weChatInfo);

  /** 查询 */
  List<WeChatInfo> selectAll();
}
