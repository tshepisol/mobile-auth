
package za.co.pps.auth.endpoints.ppsi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Generated("org.jsonschema2pojo")
public class Policy {

    @SerializedName("polno")
    @Expose
    private String polno;
    @SerializedName("prodsuite")
    @Expose
    private String prodsuite;
    @SerializedName("prodcode")
    @Expose
    private String prodcode;
    @SerializedName("prodname")
    @Expose
    private String prodname;
    @SerializedName("altname")
    @Expose
    private String altname;
    @SerializedName("fundrange")
    @Expose
    private String fundrange;
    @SerializedName("mv")
    @Expose
    private String mv;
    @SerializedName("mvdt")
    @Expose
    private Date mvdt;

    /**
     * 
     * @return
     *     The polno
     */
    public String getPolno() {
        return polno;
    }

    /**
     * 
     * @param polno
     *     The polno
     */
    public void setPolno(String polno) {
        this.polno = polno;
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
     *     The prodcode
     */
    public String getProdcode() {
        return prodcode;
    }

    /**
     * 
     * @param prodcode
     *     The prodcode
     */
    public void setProdcode(String prodcode) {
        this.prodcode = prodcode;
    }

    /**
     * 
     * @return
     *     The prodname
     */
    public String getProdname() {
        return prodname;
    }

    /**
     * 
     * @param prodname
     *     The prodname
     */
    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    /**
     * 
     * @return
     *     The altname
     */
    public String getAltname() {
        return altname;
    }

    /**
     * 
     * @param altname
     *     The altname
     */
    public void setAltname(String altname) {
        this.altname = altname;
    }

    /**
     * 
     * @return
     *     The fundrange
     */
    public String getFundrange() {
        return fundrange;
    }

    /**
     * 
     * @param fundrange
     *     The fundrange
     */
    public void setFundrange(String fundrange) {
        this.fundrange = fundrange;
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


    public Date getMvdt() {
        return mvdt;
    }

    public void setMvdt(Date mvdt) {
        this.mvdt = mvdt;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "polno='" + polno + '\'' +
                ", prodsuite='" + prodsuite + '\'' +
                ", prodcode='" + prodcode + '\'' +
                ", prodname='" + prodname + '\'' +
                ", altname='" + altname + '\'' +
                ", fundrange='" + fundrange + '\'' +
                ", mv='" + mv + '\'' +
                ", mvdt=" + mvdt +
                '}';
    }
}
