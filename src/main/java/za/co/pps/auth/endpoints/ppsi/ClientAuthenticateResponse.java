
package za.co.pps.auth.endpoints.ppsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@XmlRootElement
public class ClientAuthenticateResponse
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("cno")
    @Expose
    private String cno;

    @SerializedName("ino")
    @Expose
    private String ino;

    @SerializedName("imeistatus")
    @Expose
    private String imeistatus;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idtype")
    @Expose
    private String idtype;
    @SerializedName("prodsuite")
    @Expose
    private String prodsuite;
    @SerializedName("ppsmember")
    @Expose
    private String ppsmember;
    @SerializedName("mvdt")
    @Expose
    private Date mvdt;
    @SerializedName("mv")
    @Expose
    private String mv;
    @SerializedName("policies")
    @Expose
    @Valid
    private List<Policy> policies = new ArrayList<Policy>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     *     The cno
     */
    public String getCno() {
        return cno;
    }

    /**
     *
     * @param cno
     *     The cno
     */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
     *
     * @return
     *     The ino
     */
    public String getIno() {
        return ino;
    }

    /**
     *
     * @param ino
     *     The ino
     */
    public void setIno(String ino) {
        this.ino = ino;
    }

    /**
     *
     * @return
     *     The imeistatus
     */
    public String getImeistatus() {
        return imeistatus;
    }

    /**
     *
     * @param imeistatus
     *     The imeistatus
     */
    public void setImeistatus(String imeistatus) {
        this.imeistatus = imeistatus;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The fname
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @param fname
     *     The fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     *
     * @return
     *     The lname
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @param lname
     *     The lname
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     *
     * @return
     *     The fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     *
     * @param fullname
     *     The fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     *     The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     *     The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The idtype
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     *
     * @param idtype
     *     The idtype
     */
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    /**
     *
     * @return
     *     The prodsuite
     */
    public String getProdsuite() {
        return prodsuite;
    }

    /**
     *
     * @param prodsuite
     *     The prodsuite
     */
    public void setProdsuite(String prodsuite) {
        this.prodsuite = prodsuite;
    }

    /**
     *
     * @return
     *     The ppsmember
     */
    public String getPpsmember() {
        return ppsmember;
    }

    /**
     *
     * @param ppsmember
     *     The ppsmember
     */
    public void setPpsmember(String ppsmember) {
        this.ppsmember = ppsmember;
    }

    /**
     *
     * @return
     *     The mvdt
     */
    public Date getMvdt() {
        return mvdt;
    }

    /**
     *
     * @param mvdt
     *     The mvdt
     */
    public void setMvdt(Date mvdt) {
        this.mvdt = mvdt;
    }

    /**
     *
     * @return
     *     The mv
     */
    public String getMv() {
        return mv;
    }

    /**
     *
     * @param mv
     *     The mv
     */
    public void setMv(String mv) {
        this.mv = mv;
    }

    /**
     *
     * @return
     *     The policies
     */
    public List<Policy> getPolicies() {
        return policies;
    }

    /**
     *
     * @param policies
     *     The policies
     */
    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }


    @Override
    public String toString() {
        return "ClientAuthenticateResponse{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", cno='" + cno + '\'' +
                ", ino='" + ino + '\'' +
                ", imeistatus='" + imeistatus + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id='" + id + '\'' +
                ", idtype='" + idtype + '\'' +
                ", prodsuite='" + prodsuite + '\'' +
                ", ppsmember='" + ppsmember + '\'' +
                ", mvdt=" + mvdt +
                ", mv='" + mv + '\'' +
                ", policies=" + policies +
                '}';
    }
}
