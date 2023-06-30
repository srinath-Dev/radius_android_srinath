import com.google.gson.annotations.SerializedName

data class Property (

	@SerializedName("facilities") val facilities : List<Facilities>,
	@SerializedName("exclusions") val exclusions : List<List<Exclusions>>
)