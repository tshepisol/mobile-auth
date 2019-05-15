
package za.co.pps.auth.endpoints.ppsi;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
@XmlRootElement
public class ClientValidateRequest {

    @SerializedName("ppsmember")
    @Expose
    private String ppsmember;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("legalname")
    @Expose
    private String legalname;

    public ClientValidateRequest(){}

    public ClientValidateRequest(String ppsmember, String id) {
        this.ppsmember = ppsmember;
        this.id = id;
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
     *     The legalname
     */
    public String getLegalname() {
        return legalname;
    }

    /**
     * 
     * @param legalname
     *     The legalname
     */
    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

}
