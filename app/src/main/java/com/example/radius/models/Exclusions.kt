import com.google.gson.annotations.SerializedName


data class Exclusions (

	@SerializedName("facility_id") val facility_id : String,
	@SerializedName("options_id") val options_id : String
)