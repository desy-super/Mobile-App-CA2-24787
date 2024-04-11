package com.example.moviebooking24787

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class movie : Parcelable {
    @JvmField
    val name: String?
    @JvmField
    val image: String?
    @JvmField
    val certification: String?
    @JvmField
    val description: String?
    @JvmField
    val starring: Array<String>?
    @JvmField
    val seatsRemaining: Int
    @JvmField
    val runningTime: Int

    // Constructor for creating a new Movie instance
    constructor(
        name: String?,
        image: String?,
        certification: String?,
        description: String?,
        starring: Array<String>?,
        seatsRemaining: Int,
        runningTime: Int
    ) {
        this.name = name
        this.image = image
        this.certification = certification
        this.description = description
        this.starring = starring
        this.seatsRemaining = seatsRemaining
        this.runningTime = runningTime
    }

    // Constructor for creating a Movie instance from a Parcel
    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        image = `in`.readString()
        certification = `in`.readString()
        description = `in`.readString()
        starring = `in`.createStringArray()
        seatsRemaining = `in`.readInt()
        runningTime = `in`.readInt()
    }

    // Required Parcelable method
    override fun describeContents(): Int {
        return 0
    }

    // Write the Movie object to a Parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(image)
        dest.writeString(certification)
        dest.writeString(description)
        dest.writeStringArray(starring)
        dest.writeInt(seatsRemaining)
        dest.writeInt(runningTime)
    }

    // Companion object for creating Movie instances from a Parcel
    companion object CREATOR : Creator<movie> {
        override fun createFromParcel(`in`: Parcel): movie {
            return movie(`in`)
        }

        override fun newArray(size: Int): Array<movie?> {
            return arrayOfNulls(size)
        }
    }
}