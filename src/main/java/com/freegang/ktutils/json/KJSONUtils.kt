package com.freegang.ktutils.json

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.Reader
import java.io.Writer

@FunctionalInterface
interface JSONObjectForeachFunction {
    fun invoke(entry: Map.Entry<String, Any?>)
}

object KJSONUtils {

    /// Json ///
    @JvmStatic
    fun parse(json: String): JSONObject {
        return try {
            JSONObject(json)
        } catch (e: JSONException) {
            JSONObject()
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getString(json: JSONObject, key: String, default: String = ""): String {
        return try {
            if (json.isNull(key))
                default
            else
                json.getString(key)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getInt(json: JSONObject, key: String, default: Int = 0): Int {
        return try {
            if (json.isNull(key))
                default
            else
                json.getInt(key)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getLong(json: JSONObject, key: String, default: Long = 0L): Long {
        return try {
            if (json.isNull(key))
                default
            else
                json.getLong(key)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getDouble(json: JSONObject, key: String, default: Double = 0.0): Double {
        return try {
            if (json.isNull(key))
                default
            else
                json.getDouble(key)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getBoolean(json: JSONObject, key: String, default: Boolean = false): Boolean {
        return try {
            if (json.isNull(key))
                default
            else
                json.getBoolean(key)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    fun getJSONObject(json: JSONObject, key: String): JSONObject {
        return try {
            json.getJSONObject(key)
        } catch (e: JSONException) {
            JSONObject()
        }
    }

    @JvmStatic
    fun getJSONArray(json: JSONObject, key: String): JSONArray {
        return try {
            json.getJSONArray(key)
        } catch (e: JSONException) {
            JSONArray()
        }
    }

    @JvmStatic
    fun getJSONArrays(json: JSONObject, key: String): Array<JSONObject> {
        val array = getJSONArray(json, key)
        if (array.length() == 0) return emptyArray()
        return Array(array.length()) { array.getJSONObject(it) }
    }

    @JvmStatic
    fun toMap(json: JSONObject): Map<String, Any?> {
        val iterator = json.keys().iterator()
        val map = mutableMapOf<String, Any?>()
        while (iterator.hasNext()) {
            val key = iterator.next()
            map[key] = json.get(key)
        }
        return map
    }

    @JvmStatic
    fun mapToJson(map: Map<String, Any?>): JSONObject {
        val json = JSONObject()
        map.forEach { (t, u) -> json.put(t, u) }
        return json
    }

    @JvmStatic
    fun forEach(json: JSONObject, block: JSONObjectForeachFunction) {
        val iterator = json.keys().iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            block.invoke(object : Map.Entry<String, Any?> {
                override val key: String
                    get() = key
                override val value: Any?
                    get() = json.get(key)
            })
        }
    }

    @JvmStatic
    fun isNull(json: JSONObject, key: String): Boolean {
        return json.isNull(key)
    }

    @JvmStatic
    fun hasKey(json: JSONObject, key: String): Boolean {
        return json.has(key)
    }

    @JvmStatic
    fun isEmpty(json: JSONObject): Boolean {
        val jsonStr = json.toString()
        return json == JSONObject.NULL || jsonStr == "{}" || jsonStr == "null" || jsonStr == ""
    }

    /// Json Array ///
    @JvmStatic
    fun parseArray(json: String): JSONArray {
        return try {
            JSONArray(json)
        } catch (e: JSONException) {
            JSONArray()
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getString(array: JSONArray, index: Int, default: String = ""): String {
        return try {
            if (array.isNull(index))
                default
            else
                array.getString(index)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getInt(array: JSONArray, index: Int, default: Int = 0): Int {
        return try {
            if (array.isNull(index))
                default
            else
                array.getInt(index)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getLong(array: JSONArray, index: Int, default: Long = 0): Long {
        return try {
            if (array.isNull(index))
                default
            else
                array.getLong(index)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getDouble(array: JSONArray, index: Int, default: Double = 0.0): Double {
        return try {
            if (array.isNull(index))
                default
            else
                array.getDouble(index)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getBoolean(array: JSONArray, index: Int, default: Boolean = false): Boolean {
        return try {
            if (array.isNull(index))
                default
            else
                array.getBoolean(index)
        } catch (e: JSONException) {
            default
        }
    }

    @JvmStatic
    fun getJSONObject(array: JSONArray, index: Int): JSONObject {
        return try {
            array.getJSONObject(index)
        } catch (e: JSONException) {
            JSONObject()
        }
    }

    @JvmStatic
    fun getJSONArray(array: JSONArray, index: Int): JSONArray {
        return try {
            array.getJSONArray(index)
        } catch (e: JSONException) {
            JSONArray()
        }
    }

    @JvmStatic
    fun toJSONObjects(array: JSONArray): Array<JSONObject> {
        if (array.length() == 0) return emptyArray()
        return Array(array.length()) { array.getJSONObject(it) }
    }

    @JvmStatic
    fun toMaps(array: JSONArray): List<Map<String, Any?>> {
        if (array.length() == 0) return emptyList()
        val list = mutableListOf<Map<String, Any?>>()
        for (i in 0 until array.length()) {
            list.add(toMap(getJSONObject(array, i)))
        }
        return list
    }

    @JvmStatic
    fun isNull(array: JSONArray, index: Int): Boolean {
        return array.isNull(index)
    }

    @JvmStatic
    fun isEmpty(array: JSONArray): Boolean {
        if (array.length() == 0) return true
        val jsonStr = array.toString()
        return jsonStr == "[]" || jsonStr == "null" || jsonStr == ""
    }


    /// factory ///
    class Factory(json: String) {
        private var mJsonObject: JSONObject? = null
        private var mJsonArray: JSONArray? = null

        constructor(jsonObject: JSONObject) : this("") {
            mJsonObject = jsonObject
        }

        constructor(jsonArray: JSONArray) : this("") {
            mJsonArray = jsonArray
        }


        init {
            if (json.isNotBlank()) {
                if (json[0] == '{') {
                    mJsonObject = parse(json)
                } else {
                    mJsonArray = parseArray(json)
                }
            }
        }

        fun next(key: String): Factory {
            val jsonObject = getJSONObject(mJsonObject!!, key)
            if (isEmpty(jsonObject)) {
                mJsonArray = getJSONArray(mJsonObject!!, key)
            } else {
                mJsonObject = jsonObject
            }
            return this
        }

        fun next(index: Int): Factory {
            val jsonArray = getJSONArray(mJsonArray!!, index)
            if (isEmpty(jsonArray)) {
                mJsonObject = getJSONObject(mJsonArray!!, index)
            } else {
                mJsonArray = jsonArray
            }
            return this
        }

        val jsonObject: JSONObject get() = mJsonObject!!

        val jsonArray: JSONArray get() = mJsonArray!!
    }
}


/// Extended Json ///
fun String.parseJSON(): JSONObject {
    return KJSONUtils.parse(this)
}

fun String.readJSON(read: Reader): JSONObject {
    return try {
        val json = read.use { it.readText() }
        JSONObject(json)
    } catch (e: Exception) {
        JSONObject()
    }
}

fun JSONObject.getStringOrDefault(key: String, default: String = ""): String {
    return KJSONUtils.getString(this, key, default)
}

fun JSONObject.getIntOrDefault(key: String, default: Int = 0): Int {
    return KJSONUtils.getInt(this, key, default)
}

fun JSONObject.getLongOrDefault(key: String, default: Long = 0L): Long {
    return KJSONUtils.getLong(this, key, default)
}

fun JSONObject.getDoubleOrDefault(key: String, default: Double = 0.0): Double {
    return KJSONUtils.getDouble(this, key, default)
}

fun JSONObject.getBooleanOrDefault(key: String, default: Boolean = false): Boolean {
    return KJSONUtils.getBoolean(this, key, default)
}

fun JSONObject.getJSONObjectOrDefault(key: String, default: JSONObject = JSONObject()): JSONObject {
    return try {
        this.getJSONObject(key)
    } catch (e: JSONException) {
        default
    }
}

fun JSONObject.getJSONArrayOrDefault(key: String, default: JSONArray = JSONArray()): JSONArray {
    return try {
        this.getJSONArray(key)
    } catch (e: JSONException) {
        default
    }
}

fun JSONObject.toMap(): Map<String, Any?> {
    return KJSONUtils.toMap(this)
}

fun Map<String, Any?>.toJSONObject(): JSONObject {
    val json = JSONObject()
    this.forEach { entry ->
        try {
            json.put(entry.key, entry.value)
        } catch (e: Exception) {
            json.put(entry.key, null)
        }
    }
    return json
}

fun JSONObject.forEach(block: JSONObjectForeachFunction) {
    KJSONUtils.forEach(this, block)
}

inline fun JSONObject.forEach(crossinline block: (key: String, valeu: Any?) -> Unit) {
    KJSONUtils.forEach(this, object : JSONObjectForeachFunction {
        override fun invoke(entry: Map.Entry<String, Any?>) {
            block.invoke(entry.key, entry.value)
        }
    })
}

fun JSONObject.write(out: Writer): Boolean {
    return try {
        out.use { it.write(this.toString()) }
        true
    } catch (e: Exception) {
        false
    }
}

val JSONObject.isEmpty: Boolean
    get() = KJSONUtils.isEmpty(this)

/// Extended Json Array ///
fun String.parseJSONArray(): JSONArray {
    return KJSONUtils.parseArray(this)
}

fun String.readJSONArray(read: Reader): JSONArray {
    return try {
        val json = read.use { it.readText() }
        JSONArray(json)
    } catch (e: Exception) {
        JSONArray()
    }
}

fun JSONArray.getStringOrDefault(index: Int, default: String = ""): String {
    return KJSONUtils.getString(this, index, default)
}

fun JSONArray.getIntOrDefault(index: Int, default: Int = 0): Int {
    return KJSONUtils.getInt(this, index, default)
}

fun JSONArray.getLongOrDefault(index: Int, default: Long = 0L): Long {
    return KJSONUtils.getLong(this, index, default)
}

fun JSONArray.getDoubleOrDefault(index: Int, default: Double = 0.0): Double {
    return KJSONUtils.getDouble(this, index, default)
}

fun JSONArray.getBooleanOrDefault(index: Int, default: Boolean = false): Boolean {
    return KJSONUtils.getBoolean(this, index, default)
}

fun JSONArray.getJSONObjectOrDefault(index: Int, default: JSONObject = JSONObject()): JSONObject {
    return try {
        this.getJSONObject(index)
    } catch (e: JSONException) {
        default
    }
}

fun JSONArray.getJSONArrayOrDefault(index: Int, default: JSONArray = JSONArray()): JSONArray {
    return try {
        this.getJSONArray(index)
    } catch (e: JSONException) {
        default
    }
}

fun JSONArray.toMaps(): List<Map<String, Any?>> {
    return KJSONUtils.toMaps(this)
}

fun JSONArray.write(out: Writer): Boolean {
    return try {
        out.use { it.write(this.toString()) }
        true
    } catch (e: Exception) {
        false
    }
}

val JSONArray.isEmpty: Boolean
    get() = KJSONUtils.isEmpty(this)

fun JSONArray.firstJsonObject(default: JSONObject = JSONObject()): JSONObject {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getJSONObject(this, 0)
}

fun JSONArray.firstStringOrDefault(default: String = ""): String {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getString(this, 0, default)
}

fun JSONArray.firstIntOrDefault(default: Int = 0): Int {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getInt(this, 0, default)
}

fun JSONArray.firstLongOrDefault(default: Long = 0L): Long {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getLong(this, 0, default)
}

fun JSONArray.firstDoubleOrDefault(default: Double = 0.0): Double {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getDouble(this, 0, default)
}

fun JSONArray.firstBooleanOrDefault(default: Boolean = false): Boolean {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getBoolean(this, 0, default)
}

fun JSONArray.lastJsonObject(default: JSONObject = JSONObject()): JSONObject {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getJSONObject(this, this.length() - 1)
}

fun JSONArray.lastStringOrDefault(default: String = ""): String {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getString(this, this.length() - 1, default)
}

fun JSONArray.lastIntOrDefault(default: Int = 0): Int {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getInt(this, this.length() - 1, default)
}

fun JSONArray.lastLongOrDefault(default: Long = 0L): Long {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getLong(this, this.length() - 1, default)
}

fun JSONArray.lastDoubleOrDefault(default: Double = 0.0): Double {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getDouble(this, this.length() - 1, default)
}

fun JSONArray.lastBooleanOrDefault(default: Boolean = false): Boolean {
    if (this.length() == 0 || this.isEmpty) return default
    return KJSONUtils.getBoolean(this, this.length() - 1, default)
}

fun JSONArray.toJSONObjectArray(): Array<JSONObject> {
    return KJSONUtils.toJSONObjects(this)
}