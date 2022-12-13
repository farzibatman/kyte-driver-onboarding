package com.jmalik.kyte.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseAuditableEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column(name = "created_at")
  private Long createdAt;

  @Column(name = "updated_at")
  private Long updatedAt;

  @Version
  @Column(name = "version")
  private Long version;

  @PrePersist
  public void prePersist() {
    this.version = 0l;
    this.createdAt = System.currentTimeMillis();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = System.currentTimeMillis();
  }
}