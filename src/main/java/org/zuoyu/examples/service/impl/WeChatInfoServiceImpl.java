package org.zuoyu.examples.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.zuoyu.examples.model.WeChatInfo;
import org.zuoyu.examples.repository.WeChatInfoRepository;
import org.zuoyu.examples.service.IWeChatInfoService;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 上午9:45
 * @description .
 */
@Service
class WeChatInfoServiceImpl implements IWeChatInfoService {

  private final WeChatInfoRepository weChatInfoRepository;

  WeChatInfoServiceImpl(WeChatInfoRepository weChatInfoRepository) {
    this.weChatInfoRepository = weChatInfoRepository;
  }

  @Override
  public WeChatInfo save(WeChatInfo weChatInfo) {
    return weChatInfoRepository.save(weChatInfo);
  }

  @Override
  public void deleteById(Integer weChatId) {
    weChatInfoRepository.makeDeleted(weChatId);
  }

  @Override
  public WeChatInfo updateById(WeChatInfo weChatInfo) {
    Assert.notNull(weChatInfo.getWeChatInfoId(), "weChatInfoId can't is null！");
    return weChatInfoRepository.saveAndFlush(weChatInfo);
  }

  @Override
  public List<WeChatInfo> selectAll() {
    return weChatInfoRepository.findAll();
  }
}
