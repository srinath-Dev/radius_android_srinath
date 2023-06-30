import com.google.gson.annotations.SerializedName


data class Facilities (

	@SerializedName("facility_id") val facility_id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("options") val options : List<Options>
)