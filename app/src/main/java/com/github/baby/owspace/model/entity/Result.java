package com.github.baby.owspace.model.entity;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/21
 * owspace
 */
public class Result {

    /**
     * status : ok
     * datas : {"id":"292296","uid":"2132","name":"","title":"一位演员也是\r\n一个世界","excerpt":"耀眼的光环总让我们以为明星都是空洞的，而姚晨却以某种平民精神，试图理解世界、观察自身。本期\u201c十三邀\u201d，许知远专访姚晨。","lead":"腾讯新闻和单向空间联合出品的谈话节目《十三邀》，是许知远对这个时代所做的新思考。这是第二期。在这一期，姚晨来到位于北京花家地的单向空间，用她自己的话说，这是许知远的\u201c地盘\u201d。长达四个小时的对话，他们都聊了什么？","model":"2","position":"0","thumbnail":"http://img.owspace.com/Public/uploads/Picture/2016-07-13/5785d02bd885f.jpg","create_time":"1468388678","update_time":"2016/07/13","video":"http://img.owspace.com/V_poa248777_1468383459.0986272.mp4","fm":"","link_url":"","view":"7.6w","share":"http://static.owspace.com/wap/292296.html","comment":"89","good":"161","bookmark":"0","fm_play":"","content":"<p>\u201c我觉得我们都挺容易去概念化地理解一个人。\u201d许知远在采访后回忆。他以对娱乐明星\u201c空洞\u201d的代入感开始，到同姚晨共话黑泽明笔下的\u201c蛤蟆油\u201d；以姚晨穿着的那双小白鞋为初印象，到头脑中反复回旋着曾经小镇姑娘式的生活，可以说，他在头脑中已经重新透视了姚晨所代表的文化符号。这一过程，也许你认为是许知远的偏见，其实，是我们每个人的偏见。<br/><\/p><h2>主持人：<\/h2><p>许知远，作家，单向空间联合创始人。2000 年毕业于北京大学计算机系。曾任《经济观察报》主笔、《商业周刊/中文版》执行主编。已出版作品《那些忧伤的年轻人》《一个游荡者的世界》《祖国的陌生人》等。<\/p><p><img src=\"http://img.owspace.com/Public/uploads/Editor/2016-07-13/1468386530268929.jpg\" width=\"720\" height=\"1280\" /><\/p><h2>第二期嘉宾：<\/h2><p>姚晨，中国内地女演员，联合国难民署中国亲善大使。第 2 届华鼎奖电视剧盛典最佳女主角，第 18 届北京大学生电影节最受欢迎女演员，代表作有《武林外传》《潜伏》《离婚律师》等。2013 年、2014 年入围美国《时代》周刊\u201c全球 100 位最具影响力人物\u201d，2014 年当选；2015 年入围该杂志评选的\u201c全球 30 位最具网络影响力人物\u201d；倍耐力年历首位华人封面。2016 年世界经济论坛水晶奖获得者，也是首位获得该奖项的华人女性; 2016 年世界经济论坛\u201c全球青年领袖\u201d中国唯一入选女星。<\/p><p><img src=\"http://img.owspace.com/Public/uploads/Editor/2016-07-13/1468386530405683.jpg\" width=\"720\" height=\"1280\" /><\/p><p>\u201c过去十年，还有一个重要的问题是平民的兴起，平民从来没有获得过这样的一次表达机会，而姚晨身上具有某种平民精神，同时她又理解自己是怎么回事。\u201c许知远在第二期《十三邀》中这样总结。的确，在被科技引领的今天，每一个人都有可能在下一秒被千万人聆听，这是属于普通人的特别时代。<\/p><p>许知远说，他是一个不太靠谱的作家，试图捕捉时代的精神，却又常常厌恶时代的流行情绪；他也是一个勉强的创业者，努力获得商业上的成功，却又不完全相信商业的逻辑。这个时代在他眼中，有着过分娱乐化和浅薄的一面，所以，他试图寻找一种方式，打破大家思维中的惯性，同时，他也希望了解这个时代中，新的动力、情绪和人们的内心世界。<\/p><p>于是，腾讯新闻联合单向空间，寻找了 13 位在一定意义上来说具有模板作用的个人，向他们发出邀请，希望通过访谈的形式，观察他们的行为，分享他们的经验和心得。从正在发生的样本出发，探求中国发展的切片。<\/p><p><img src=\"http://img.owspace.com/Public/uploads/Editor/2016-07-13/1468386530190980.jpg\" width=\"720\" height=\"1280\" /><\/p><p><img width=\"1000\" height=\"376\" src=\"http://img.owspace.com/Public/uploads/Picture/2016-06-29/5773a24ca98ce.jpg\" jump_url=\"https://item.taobao.com/item.htm?spm=0.0.0.0.UAMLwv&id=533874234260&wezeit_jump=2&dandu_adv=8\" /><\/p>","template":"","link_id":"0","extend":null,"parseXML":1,"html5":"http://static.owspace.com/wap/292296.html","author":"单读视频","tpl":2,"avatar":"http://img.owspace.com/Public/static/avatar/4.png","category":"TO WATCH","hot_comments":[]}
     * msg :
     * code : 0
     */

    private String status;
    private String msg;
    private int code;

    public static class Data<T> extends Result{
        private T datas;

        public T getDatas() {
            return datas;
        }

        public void setDatas(T datas) {
            this.datas = datas;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
