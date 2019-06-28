package cn.meeler.test.controller.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

public class Picture {
    @Field
    private String id;
    @Field
    private Long userId;
    @Field
    private Integer orientation;
    @Field
    private String picturesize;
    @Field
    private String specification;
    @Field
    private Integer filesize;
    @Field
    private String filepath;
    @Field
    private String filehash;
    @Field
    private Long evidenceid;
    @Field
    private Integer flag;
    @Field
    private Integer auditflag;
    @Field
    private String auditcontent;
    @Field
    private Integer setprice;
    @Field
    private String title;
    @Field
    private Long classificationid;
    @Field
    private Long picturefolderid;
    @Field
    private String label;
    @Field
    private Integer direction;
    @Field
    private String dominantcolor;
    @Field
    private String describes;
    @Field
    private Date uploadtime;
    @Field
    private Integer status;
    @Field
    private Integer entruststatus;
    @Field
    private Date ts;
    @Field
    private String alitag;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public String getPicturesize() {
        return picturesize;
    }

    public void setPicturesize(String picturesize) {
        this.picturesize = picturesize;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilehash() {
        return filehash;
    }

    public void setFilehash(String filehash) {
        this.filehash = filehash;
    }

    public Long getEvidenceid() {
        return evidenceid;
    }

    public void setEvidenceid(Long evidenceid) {
        this.evidenceid = evidenceid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getAuditflag() {
        return auditflag;
    }

    public void setAuditflag(Integer auditflag) {
        this.auditflag = auditflag;
    }

    public String getAuditcontent() {
        return auditcontent;
    }

    public void setAuditcontent(String auditcontent) {
        this.auditcontent = auditcontent;
    }

    public Integer getSetprice() {
        return setprice;
    }

    public void setSetprice(Integer setprice) {
        this.setprice = setprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getClassificationid() {
        return classificationid;
    }

    public void setClassificationid(Long classificationid) {
        this.classificationid = classificationid;
    }

    public Long getPicturefolderid() {
        return picturefolderid;
    }

    public void setPicturefolderid(Long picturefolderid) {
        this.picturefolderid = picturefolderid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getDominantcolor() {
        return dominantcolor;
    }

    public void setDominantcolor(String dominantcolor) {
        this.dominantcolor = dominantcolor;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEntruststatus() {
        return entruststatus;
    }

    public void setEntruststatus(Integer entruststatus) {
        this.entruststatus = entruststatus;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getAlitag() {
        return alitag;
    }

    public void setAlitag(String alitag) {
        this.alitag = alitag;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", orientation=" + orientation +
                ", picturesize='" + picturesize + '\'' +
                ", specification='" + specification + '\'' +
                ", filesize=" + filesize +
                ", filepath='" + filepath + '\'' +
                ", filehash='" + filehash + '\'' +
                ", evidenceid=" + evidenceid +
                ", flag=" + flag +
                ", auditflag=" + auditflag +
                ", auditcontent='" + auditcontent + '\'' +
                ", setprice=" + setprice +
                ", title='" + title + '\'' +
                ", classificationid=" + classificationid +
                ", picturefolderid=" + picturefolderid +
                ", label='" + label + '\'' +
                ", direction=" + direction +
                ", dominantcolor='" + dominantcolor + '\'' +
                ", describes='" + describes + '\'' +
                ", uploadtime=" + uploadtime +
                ", status=" + status +
                ", entruststatus=" + entruststatus +
                ", ts=" + ts +
                ", alitag='" + alitag + '\'' +
                '}';
    }
}
