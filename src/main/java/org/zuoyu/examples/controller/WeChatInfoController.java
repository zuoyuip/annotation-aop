package org.zuoyu.examples.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.examples.model.WeChatInfo;
import org.zuoyu.examples.service.IWeChatInfoService;
import org.zuoyu.examples.utils.Result;

/**
 * @author zuoyu
 * @date 2020/6/30
 * @time 上午11:05
 * @description 微信用户信息.
 */
@Api(value = "微信用户信息操作", tags = "WeChatInfoAPI")
@RestController
@RequestMapping("/wechat")
public class WeChatInfoController {

  private final IWeChatInfoService iWeChatInfoService;

  public WeChatInfoController(IWeChatInfoService iWeChatInfoService) {
    this.iWeChatInfoService = iWeChatInfoService;
  }

  @GetMapping
  @ApiOperation(value = "获取微信用户列表")
  public Result selectAll() {
    List<WeChatInfo> weChatInfos = iWeChatInfoService.selectAll();
    return Result.success(weChatInfos);
  }

  @PostMapping
  @ApiOperation(value = "新增微信用户")
  public Result save(WeChatInfo info) {
    iWeChatInfoService.save(info);
    return Result.success();
  }

  @PutMapping(path = "/{id}")
  @ApiOperation(value = "根据ID修改微信用户")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "id",
        value = "根据ID修改微信用户",
        dataTypeClass = Integer.class,
        required = true)
  })
  public Result updateById(WeChatInfo info, @PathVariable Integer id) {
    iWeChatInfoService.updateById(info.setWeChatInfoId(id));
    return Result.success();
  }

  @PutMapping(path = "/city/{id}")
  @ApiOperation(value = "根据ID修改微信用户的城市")
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "city",
        value = "微信用户的城市",
        dataTypeClass = String.class,
        required = true),
    @ApiImplicitParam(
        name = "id",
        value = "根据ID修改微信用户的城市",
        dataTypeClass = Integer.class,
        required = true)
  })
  public Result updateCityById(String city, @PathVariable Integer id) {
    iWeChatInfoService.updateCityById(city, id);
    return Result.success();
  }

  @DeleteMapping(path = "/{id}")
  @ApiOperation(value = "根据ID删除微信用户")
  @ApiImplicitParam(
      name = "id",
      value = "根据ID删除微信用户",
      dataTypeClass = Integer.class,
      required = true)
  public Result deleteById(@PathVariable Integer id) {
    iWeChatInfoService.deleteById(id);
    return Result.success();
  }
}
