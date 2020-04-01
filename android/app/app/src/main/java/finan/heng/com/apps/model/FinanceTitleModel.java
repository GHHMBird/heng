package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/11.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class FinanceTitleModel implements Serializable {

    @Expose
    @SerializedName("productsGenreVoList")
    public ArrayList<FinanceTitleInfo> productsGenreVoList;

}
