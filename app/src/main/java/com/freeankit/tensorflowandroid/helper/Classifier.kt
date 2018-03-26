package com.freeankit.tensorflowandroid.helper

import android.graphics.Bitmap
import android.graphics.RectF



/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 26/03/2018 (MM/DD/YYYY)
 */

/**
 * Generic interface for interacting with different recognition engines.
 */

abstract class Classifier {
    /**
     * An immutable result returned by a Classifier describing what was recognized.
     */
    inner class Recognition(
            /**
             * A unique identifier for what has been recognized. Specific to the class, not the instance of
             * the object.
             */
            val id: String?,
            /**
             * Display name for the recognition.
             */
            val title: String?,
            /**
             * A sortable score for how good the recognition is relative to others. Higher should be better.
             */
            val confidence: Float?,
            /**
             * Optional location within the source image for the location of the recognized object.
             */
            private var location: RectF?) {

        fun getLocation(): RectF {
            return RectF(location)
        }

        fun setLocation(location: RectF) {
            this.location = location
        }

        override fun toString(): String {
            var resultString = ""
            if (id != null) {
                resultString += "[$id] "
            }

            if (title != null) {
                resultString += title + " "
            }

            if (confidence != null) {
                resultString += String.format("(%.1f%%) ", confidence * 100.0f)
            }

            if (location != null) {
                resultString += location!!.toString() + " "
            }

            return resultString.trim { it <= ' ' }
        }
    }

    abstract fun recognizeImage(bitmap: Bitmap): List<Recognition>

    abstract fun enableStatLogging(debug: Boolean)

    abstract fun getStatString(): String

    abstract fun close()

}