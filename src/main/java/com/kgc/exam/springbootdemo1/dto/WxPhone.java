package com.kgc.exam.springbootdemo1.dto;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wx_phone
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class WxPhone {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_phone.wx_id
     *
     * @mbg.generated
     */
    private Long wxId;

    /**
     * Database Column Remarks:
     *   微信号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_phone.wx
     *
     * @mbg.generated
     */
    private String wx;

    /**
     * Database Column Remarks:
     *   手机号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_phone.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_phone.wx_id
     *
     * @return the value of wx_phone.wx_id
     *
     * @mbg.generated
     */
    public Long getWxId() {
        return wxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_phone.wx_id
     *
     * @param wxId the value for wx_phone.wx_id
     *
     * @mbg.generated
     */
    public void setWxId(Long wxId) {
        this.wxId = wxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_phone.wx
     *
     * @return the value of wx_phone.wx
     *
     * @mbg.generated
     */
    public String getWx() {
        return wx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_phone.wx
     *
     * @param wx the value for wx_phone.wx
     *
     * @mbg.generated
     */
    public void setWx(String wx) {
        this.wx = wx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_phone.phone
     *
     * @return the value of wx_phone.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_phone.phone
     *
     * @param phone the value for wx_phone.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}