package org.zuoyu.examples.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * @date 2020/6/24
 * @time  上午11:53
 * @description 通用字段.
 * @author zuoyu
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  /** Create time. */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(
      name = "create_time",
      columnDefinition = "timestamp not null default CURRENT_TIMESTAMP comment '创建时间'")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty("createtime")
  private Date createTime;

  /** Update time. */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Column(name = "update_time", columnDefinition = "timestamp not null comment '修改时间'")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty("updatetime")
  private Date updateTime;

  /** Delete flag. */
  @Column(name = "deleted", columnDefinition = "TINYINT default 0 comment '是否已被删除'")
  private Boolean deleted = false;

  @PrePersist
  void prePersist() {
    this.deleted = false;
    Date now = Date.from(Instant.now());
    if (this.createTime == null) {
      this.createTime = now;
    }

    if (this.updateTime == null) {
      this.updateTime = now;
    }
  }

  @PreUpdate
  void preUpdate() {
    this.updateTime = new Date();
  }

  @PreRemove
  void preRemove() {
    this.updateTime = new Date();
    this.deleted = true;
  }
}
