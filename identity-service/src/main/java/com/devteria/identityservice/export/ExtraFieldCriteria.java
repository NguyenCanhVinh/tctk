package com.devteria.identityservice.export;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExtraFieldCriteria implements Serializable {

  private static final long serialVersionUID = 1L;

  // report
  private List<String> exportFields;

  private List<String> displayFieldNames;

//  // audit
//  private InstantFilter updatedDate;
//
//  private StringFilter updatedBy;
//
//  private InstantFilter createdDate;
//
//  private StringFilter createdBy;

  public ExtraFieldCriteria() {}

  public ExtraFieldCriteria(ExtraFieldCriteria other) {
//    this.updatedDate = other.updatedDate == null ? null : other.updatedDate.copy();
//    this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
//    this.updatedBy = other.updatedBy == null ? null : other.updatedBy.copy();
//    this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
    this.exportFields = other.exportFields == null ? null : new ArrayList<>(other.exportFields);
    this.displayFieldNames = other.displayFieldNames == null ? null : new ArrayList<>(other.displayFieldNames);
  }

  public List<String> getDisplayFieldNames() {
    return displayFieldNames;
  }

  public void setDisplayFieldNames(List<String> displayFieldNames) {
    this.displayFieldNames = displayFieldNames;
  }

  public List<String> getExportFields() {
    return exportFields;
  }

  public void setExportFields(List<String> exportFields) {
    this.exportFields = exportFields;
  }

//  public InstantFilter getUpdatedDate() {
//    return updatedDate;
//  }
//
//  public void setUpdatedDate(InstantFilter updatedDate) {
//    this.updatedDate = updatedDate;
//  }
//
//  public StringFilter getUpdatedBy() {
//    return updatedBy;
//  }
//
//  public void setUpdatedBy(StringFilter updatedBy) {
//    this.updatedBy = updatedBy;
//  }
//
//  public InstantFilter getCreatedDate() {
//    return createdDate;
//  }
//
//  public void setCreatedDate(InstantFilter createdDate) {
//    this.createdDate = createdDate;
//  }
//
//  public StringFilter getCreatedBy() {
//    return createdBy;
//  }
//
//  public void setCreatedBy(StringFilter createdBy) {
//    this.createdBy = createdBy;
//  }


}