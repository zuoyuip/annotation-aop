package org.zuoyu.examples.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @date 2020/6/24
 * @time 上午11:57
 * @description 微信用户模型.
 * @author zuoyu
 */
@Data
@Entity
@ApiModel("微信用户")
@DynamicInsert
@DynamicUpdate
@Table(name = "wechatinfo", schema = "examples")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeChatInfo extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @JsonProperty("id")
  @ApiModelProperty("ID")
  private Integer weChatInfoId;

  /**
   * 普通用户的标识，对当前开发者帐号唯一
   */
  @Column(name = "openid", columnDefinition = "varchar(255) not null comment '通用户的标识，对当前开发者帐号唯一'")
  @JsonProperty("openid")
  @ApiModelProperty("普通用户的标识，对当前开发者帐号唯一")
  private String openId;

  /**
   * 普通用户昵称
   */
  @Column(name = "nickname", columnDefinition = "varchar(100) not null comment '普通用户昵称'")
  @JsonProperty("nickname")
  @ApiModelProperty("普通用户昵称")
  private String nickName;

  /**
   * 语言
   */
  @Column(name = "language", columnDefinition = "varchar(50) comment '语言'")
  @JsonProperty("language")
  @ApiModelProperty("语言")
  private String language;

  /**
   * 普通用户性别，1为男性，2为女性
   */
  @Column(name = "sex", columnDefinition = "varchar(1) not null comment '普通用户性别，1为男性，2为女性'")
  @JsonProperty("sex")
  @ApiModelProperty("普通用户性别，1为男性，2为女性")
  private Integer sex;

  /**
   * 普通用户个人资料填写的省份
   */
  @Column(name = "province", columnDefinition = "varchar(50) comment '普通用户个人资料填写的省份'")
  @JsonProperty("province")
  @ApiModelProperty("普通用户个人资料填写的省份")
  private String province;

  /**
   * 普通用户个人资料填写的城市
   */
  @Column(name = "city", columnDefinition = "varchar(50) comment '普通用户个人资料填写的城市'")
  @JsonProperty("city")
  @ApiModelProperty("普通用户个人资料填写的城市")
  private String city;

  /**
   * 国家，如中国为CN
   */
  @Column(name = "country", columnDefinition = "varchar(50) comment '国家，如中国为CN'")
  @JsonProperty("country")
  @ApiModelProperty("国家")
  private String country;

  /**
   * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
   */
  @Column(name = "headimgurl", columnDefinition = "varchar(510) comment '用户头像'")
  @JsonProperty("headimgurl")
  @ApiModelProperty("用户头像")
  private String headImgUrl;

  /**
   * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
   */
  @Column(name = "unionid", columnDefinition = "varchar(255) not null comment '用户统一标识'")
  @JsonProperty("unionid")
  @ApiModelProperty("用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。")
  private String unionId;
}
