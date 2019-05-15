/**
 * PPSi Services
 * TODO
 *
 * OpenAPI spec version: 1.0.0
 * Contact: mkhooza@pps.co.za
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package za.co.pps.auth.endpoints.ppsi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * ClientValidationResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-09-08T14:17:23.359+02:00")
public class ClientValidationResponse {
  @SerializedName("cno")
  private String cno = null;

  @SerializedName("imeistatus")
  private String imeistatus = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("fname")
  private String fname = null;

  @SerializedName("lname")
  private String lname = null;

  @SerializedName("fullname")
  private String fullname = null;

  @SerializedName("email")
  private String email = null;

  @SerializedName("mobile")
  private String mobile = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("idtype")
  private String idtype = null;

  @SerializedName("dob")
  private String dob = null;

  @SerializedName("gender")
  private String gender = null;

  @SerializedName("prodsuite")
  private String prodsuite = null;

  @SerializedName("ppsmember")
  private String ppsmember = null;

  @SerializedName("mvdt")
  private String mvdt = null;

  @SerializedName("mv")
  private String mv = null;

  public ClientValidationResponse cno(String cno) {
    this.cno = cno;
    return this;
  }

  @SerializedName("policies")
  @Expose
  @Valid
  private List<Policy> policies = new ArrayList<Policy>();

   /**
   * Get cno
   * @return cno
  **/
  @ApiModelProperty(example = "106106123456", value = "")
  public String getCno() {
    return cno;
  }

  public void setCno(String cno) {
    this.cno = cno;
  }

  public ClientValidationResponse imeistatus(String imeistatus) {
    this.imeistatus = imeistatus;
    return this;
  }

   /**
   * Get imeistatus
   * @return imeistatus
  **/
  @ApiModelProperty(example = "verified/unverified/unregistered", value = "")
  public String getImeistatus() {
    return imeistatus;
  }

  public void setImeistatus(String imeistatus) {
    this.imeistatus = imeistatus;
  }

  public ClientValidationResponse type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "I/C", value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ClientValidationResponse title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "Mr", value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ClientValidationResponse fname(String fname) {
    this.fname = fname;
    return this;
  }

   /**
   * Get fname
   * @return fname
  **/
  @ApiModelProperty(example = "Joe /blank for corporate", value = "")
  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public ClientValidationResponse lname(String lname) {
    this.lname = lname;
    return this;
  }

   /**
   * Get lname
   * @return lname
  **/
  @ApiModelProperty(example = "Soap / blank for corporate", value = "")
  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public ClientValidationResponse fullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

   /**
   * Get fullname
   * @return fullname
  **/
  @ApiModelProperty(example = "title intitials lname / corporatename", value = "")
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public ClientValidationResponse email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "email address on record if it exists", value = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ClientValidationResponse mobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

   /**
   * Get mobile
   * @return mobile
  **/
  @ApiModelProperty(example = "mobile number on record if it exists", value = "")
  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public ClientValidationResponse id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "0001010001001/TR#/CK#/PP#", value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ClientValidationResponse idtype(String idtype) {
    this.idtype = idtype;
    return this;
  }

   /**
   * Get idtype
   * @return idtype
  **/
  @ApiModelProperty(example = "id/passport/other", value = "")
  public String getIdtype() {
    return idtype;
  }

  public void setIdtype(String idtype) {
    this.idtype = idtype;
  }

  public ClientValidationResponse dob(String dob) {
    this.dob = dob;
    return this;
  }

   /**
   * Get dob
   * @return dob
  **/
  @ApiModelProperty(example = "yyyy-MM-dd", value = "")
  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public ClientValidationResponse gender(String gender) {
    this.gender = gender;
    return this;
  }

   /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(example = "M/F", value = "")
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ClientValidationResponse prodsuite(String prodsuite) {
    this.prodsuite = prodsuite;
    return this;
  }

   /**
   * Get prodsuite
   * @return prodsuite
  **/
  @ApiModelProperty(example = "OPN/PPS", value = "")
  public String getProdsuite() {
    return prodsuite;
  }

  public void setProdsuite(String prodsuite) {
    this.prodsuite = prodsuite;
  }

  public ClientValidationResponse ppsmember(String ppsmember) {
    this.ppsmember = ppsmember;
    return this;
  }

   /**
   * Get ppsmember
   * @return ppsmember
  **/
  @ApiModelProperty(example = "pps member # if available", value = "")
  public String getPpsmember() {
    return ppsmember;
  }

  public void setPpsmember(String ppsmember) {
    this.ppsmember = ppsmember;
  }

  public ClientValidationResponse mvdt(String mvdt) {
    this.mvdt = mvdt;
    return this;
  }

   /**
   * Get mvdt
   * @return mvdt
  **/
  @ApiModelProperty(example = "yyyy-MM-dd", value = "")
  public String getMvdt() {
    return mvdt;
  }

  public void setMvdt(String mvdt) {
    this.mvdt = mvdt;
  }

  public ClientValidationResponse mv(String mv) {
    this.mv = mv;
    return this;
  }

   /**
   * Get mv
   * @return mv
  **/
  @ApiModelProperty(example = "123456789012.12", value = "")
  public String getMv() {
    return mv;
  }

  public void setMv(String mv) {
    this.mv = mv;
  }


  public List<Policy> getPolicies() {
    return policies;
  }

  public void setPolicies(List<Policy> policies) {
    this.policies = policies;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientValidationResponse clientValidationResponse = (ClientValidationResponse) o;
    return Objects.equals(this.cno, clientValidationResponse.cno) &&
        Objects.equals(this.imeistatus, clientValidationResponse.imeistatus) &&
        Objects.equals(this.type, clientValidationResponse.type) &&
        Objects.equals(this.title, clientValidationResponse.title) &&
        Objects.equals(this.fname, clientValidationResponse.fname) &&
        Objects.equals(this.lname, clientValidationResponse.lname) &&
        Objects.equals(this.fullname, clientValidationResponse.fullname) &&
        Objects.equals(this.email, clientValidationResponse.email) &&
        Objects.equals(this.mobile, clientValidationResponse.mobile) &&
        Objects.equals(this.id, clientValidationResponse.id) &&
        Objects.equals(this.idtype, clientValidationResponse.idtype) &&
        Objects.equals(this.dob, clientValidationResponse.dob) &&
        Objects.equals(this.gender, clientValidationResponse.gender) &&
        Objects.equals(this.prodsuite, clientValidationResponse.prodsuite) &&
        Objects.equals(this.ppsmember, clientValidationResponse.ppsmember) &&
        Objects.equals(this.mvdt, clientValidationResponse.mvdt) &&
        Objects.equals(this.mv, clientValidationResponse.mv);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cno, imeistatus, type, title, fname, lname, fullname, email, mobile, id, idtype, dob, gender, prodsuite, ppsmember, mvdt, mv);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientValidationResponse {\n");

    sb.append("    cno: ").append(toIndentedString(cno)).append("\n");
    sb.append("    imeistatus: ").append(toIndentedString(imeistatus)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    fname: ").append(toIndentedString(fname)).append("\n");
    sb.append("    lname: ").append(toIndentedString(lname)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idtype: ").append(toIndentedString(idtype)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    prodsuite: ").append(toIndentedString(prodsuite)).append("\n");
    sb.append("    ppsmember: ").append(toIndentedString(ppsmember)).append("\n");
    sb.append("    mvdt: ").append(toIndentedString(mvdt)).append("\n");
    sb.append("    mv: ").append(toIndentedString(mv)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

