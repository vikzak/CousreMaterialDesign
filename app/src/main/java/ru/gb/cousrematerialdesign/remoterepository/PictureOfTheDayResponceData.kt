package ru.gb.cousrematerialdesign.remoterepository


import com.google.gson.annotations.SerializedName

data class PictureOfTheDayResponceData(
    val copyright: String,
    val date: String, // 2022-03-06
    val explanation: String, // This was a very unusual type of solar eclipse.  Typically, it is the Earth's Moon that eclipses the Sun.  In 2012, though, the planet Venus took a turn.  Like a solar eclipse by the Moon, the phase of Venus became a continually thinner crescent as Venus became increasingly better aligned with the Sun.  Eventually the alignment became perfect and the phase of Venus dropped to zero.  The dark spot of Venus crossed our parent star.  The situation could technically be labeled a Venusian annular eclipse with an extraordinarily large ring of fire.  Pictured here during the occultation, the Sun was imaged in three colors of ultraviolet light by the Earth-orbiting Solar Dynamics Observatory, with the dark region toward the right corresponding to a coronal hole. Hours later, as Venus continued in its orbit, a slight crescent phase appeared again.  The next Venusian transit across the Sun will occur in 2117.
    val hdurl: String, // https://apod.nasa.gov/apod/image/2203/SunVenusUv3_SdoDove_960.jpg
    @SerializedName("media_type")
    val mediaType: String, // image
    @SerializedName("service_version")
    val serviceVersion: String, // v1
    val title: String, // Venus and the Triply Ultraviolet Sun
    val url: String // https://apod.nasa.gov/apod/image/2203/SunVenusUv3_SdoDove_960.jpg
)