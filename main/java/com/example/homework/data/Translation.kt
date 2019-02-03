package com.example.homework.data

import java.io.Serializable
import java.util.ArrayList

class Translation {
    var date: String? = null

    //    private DetailBean detailBean = new DetailBean();
    //
    //    public void setDetailBean (DetailBean detailBean) {
    //        this.detailBean = detailBean;
    //    }
    //
    //    public DetailBean getDetailBean() {
    //        return detailBean;
    //    }

    var stories: MutableList<StoriesBean> = ArrayList()
    var top_stories: MutableList<TopStoriesBean> = ArrayList()

    //Detail

    var body: String? = null
    var image_source: String? = null
    var title: String? = null
    var image: String? = null
    var share_url: String? = null
    var section_name: String? = null
    var id: Int = 0
    var js: List<String>? = null
    var images: List<String>? = null
    var css: List<String>? = null

    class StoriesBean : Serializable {

        var title: String? = null
        var isMultiPic: Boolean = false
        var id: Int = 0
        var images: List<String>? = null
    }

    class TopStoriesBean : Serializable {

        var image: String? = null
        var id: Int = 0
        var title: String? = null
    }

}
