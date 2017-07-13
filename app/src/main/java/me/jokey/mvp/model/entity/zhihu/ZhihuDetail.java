package me.jokey.mvp.model.entity.zhihu;

import java.util.List;

/**
 * Created by wz on 2017/6/21 15:33.
 * ZhihuDetail
 */


public class ZhihuDetail {

    /**
     * body : <div class="main-wrap content-wrap">
     * <div class="headline">
     * <p>
     * <div class="img-place-holder"></div>
     * <p>
     * <p>
     * <p>
     * </div>
     * <p>
     * <div class="content-inner">
     * <p>
     * <p>
     * <p>
     * <div class="question">
     * <h2 class="question-title">【动漫里的生物趣事】皮卡丘为什么能放电？（从电鳗的角度来分析）</h2>
     * <div class="answer">
     * <p>
     * <div class="meta">
     * <img class="avatar" src="http://pic2.zhimg.com/v2-5234b4d4f6784505b9ab7852bdf928a5_is.jpg">
     * <span class="author">Mr-HH，</span><span class="bio">细胞生物学/遗传学/普通生物学科普</span>
     * </div>
     * <p>
     * <div class="content">
     * <p>《精灵宝可梦》里面的电属性的宝可梦特别多，光最常见的就有绝对主角的皮卡丘，以及雷丘、闪电鸟、雷伊布、电龙等等。皮卡丘的绝招<strong>「十万伏特」</strong>大家一定印象特别深，因为火箭队几乎每集都会被皮卡丘的十万伏特给电飞。</p>
     * <p><img class="content-image" src="http://pic1.zhimg.com/v2-bac9c8ead12d45bbc6eb6e8a45c776f0_b.jpg" alt="" /></p>
     * <blockquote>神奇宝贝中的电属性宝可梦（<a class=" external" href="http://link.zhihu.com/?target=http%3A//wiki.52poke.com" target="_blank" rel="nofollow noreferrer"><span class="invisible">http://</span><span class="visible">wiki.52poke.com</span><span class="invisible"></span></a>）</blockquote>
     * <p><img class="content-image" src="http://pic1.zhimg.com/v2-82a7015d1926b135dfc99c293a6463f4_b.png" alt="" /></p>
     * <p><img class="content-image" src="http://pic1.zhimg.com/v2-fcf948790b7954731bee558b279e0ccc_b.jpg" alt="" /></p>
     * <p>皮卡丘在动画中的定位是一只能放电的小老鼠，连它的尾巴也是闪电的形状。皮卡丘要放电的时候，多数都会集中意念，然后电流从身体击出，直击对方。而放电的部位似乎就是皮卡丘脸上的两个小圆斑。</p>
     * <p><img class="content-image" src="http://pic3.zhimg.com/v2-095cbd90a8fd99d7abd51dfcacfc278e_b.png" alt="" /></p>
     * <p>网上曾经有流传一张皮卡丘的身体结构图：在皮卡丘的耳朵上、脸上，以及身体肌肉下面有着大量的<strong>放电组织</strong>。画图的作者甚至还对这些放电细胞是怎么工作的进行了猜测：皮卡丘可以随意控制这些细胞中钠离子的流动，从而产生一个<strong>瞬间的电势差</strong>并引起强有力的电流。</p>
     * <p><img class="content-image" src="http://pic3.zhimg.com/v2-7423007480b7d0b1f369efdbc60a5232_b.jpg" alt="" /></p>
     * <blockquote>皮卡丘的身体结构（Christopher Stoll deviantart）</blockquote>
     * <p>当然，我们知道神奇宝贝中的很多宝可梦在现实生活都是有原型的。所以今天，我们就来从「十万伏特」开始说起，看看皮卡丘是如何「借鉴」它们的发电本领的？</p>
     * <p><img class="content-image" src="http://pic3.zhimg.com/v2-f6e1333043d1f322b9b47ef9cf58b93e_b.jpg" alt="" /></p>
     * <p>其实说起「生物电」，那并不是某种生物的专利，而是所有的细胞生物都会的一种功能。举个最简单的例子&mdash;&mdash;我们的<strong>神经冲动</strong>，就是<strong>通过电信号来进行传导的</strong>。在静息状态下的神经细胞，由于细胞膜内外的离子浓度不同，呈现出膜内负电荷，膜外正电荷的状态，也即<strong>「内负外正」</strong>。当神经的某处受到刺激后，膜上的钠离子通道开放，钠离子大量内流，使得膜内的电位「变正」，形成<strong>「内正外负」</strong>。于是刺激处的电位便于周围的内外膜电位形成了电位差，便产生了局部电流不断向外传播出去，一直传到神经的末梢。</p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/v2-b48c9b95f11178d7c6aaa0b843a97af7_b.jpg" alt="" /></p>
     * <p>对于我们人体而言，这些电位产生及变化的幅度不过都是<strong>毫伏级</strong>，但足以应对神经传导和肌肉收缩等生理功能。</p>
     * <p>然而在我们自然界中，有少数种类的海洋生物将这种「生物电」充分的利用了起来，使之成为自己捕食、自卫的工具，它们也可称之为「电鱼」。</p>
     * <p>这些能对外发出电流的「电鱼」最常见的就是<strong>电鳗、电鳐、电鲶</strong>等，其中又以电鳗的电力最强，最高可达 300-800 伏特（比起皮卡丘的十万伏特还是要逊色不少），足以使人击晕甚至死亡。电鳗属于硬骨鱼中的辐鳍鱼纲电鳗目，栖息在南美洲的亚马逊河及奥里诺科河流域，生性昼伏夜出，以捕食小鱼为主。</p>
     * <p><img class="content-image" src="http://pic1.zhimg.com/v2-5acb9659359660b241da096a7b69d438_b.jpg" alt="" /></p>
     * <blockquote>电鳗（<a class=" external" href="http://link.zhihu.com/?target=http%3A//wikipedia.org" target="_blank" rel="nofollow noreferrer"><span class="invisible">http://</span><span class="visible">wikipedia.org</span><span class="invisible"></span></a>）</blockquote>
     * <p><img class="content-image" src="http://pic4.zhimg.com/v2-32ecdd7be55bca77ae9cb0138a1c0aeb_b.jpg" alt="" /></p>
     * <blockquote>电鳐（<a class=" external" href="http://link.zhihu.com/?target=http%3A//wikipedia.org" target="_blank" rel="nofollow noreferrer"><span class="invisible">http://</span><span class="visible">wikipedia.org</span><span class="invisible"></span></a>）</blockquote>
     * <p>其实早在达尔文当年在贝格尔号的环球旅行中，就曾经研究过电鳗。那时候他对电鳗进行了解剖，发现了这种鱼身体的 80% 空间被三个长得很像肌肉组织的器官所占据，而这些个器官实际上就是电鳗的发电组织，而它们也正是由肌肉组织所特化而成。</p>
     * <p><img class="content-image" src="http://pic1.zhimg.com/v2-14dd116b812cf6c4416e3fe37a272f1c_b.png" alt="" /></p>
     * <blockquote>达尔文的贝格尔号环球之旅（<a class=" external" href="http://link.zhihu.com/?target=http%3A//wikipedia.org" target="_blank" rel="nofollow noreferrer"><span class="invisible">http://</span><span class="visible">wikipedia.org</span><span class="invisible"></span></a>）</blockquote>
     * <p>这些发电组织里面的发电细胞，它的膜上特化了非常多的离子泵，尽管<strong>每个细胞受到刺激后只能产生约 150mV 的电位变化</strong>，但是这些发电细胞却像是一个个串联起来的小电池，总共约 6000-10000 枚肌肉细胞薄片，<strong>串联起来的最高电压可达数百伏特</strong>，在中南美洲的某种电鳗，其发电电压甚至可以高达 800 多伏。</p>
     * <p><img class="content-image" src="http://pic1.zhimg.com/v2-4926f40aea14de5324beeebb0b345654_b.jpg" alt="" /></p>
     * <blockquote>电鳗的发电组织（<a class=" wrap external" href="http://link.zhihu.com/?target=https%3A//animalphys4e.sinauer.com/boxex2001.html" target="_blank" rel="nofollow noreferrer">Animal Physiology 4e</a>）</blockquote>
     * <p>不过，电鳗可不会有事没事就把自己的「八百伏特」拿出来使，因为电量的每一次释放都需要有一个「储备」的过程。电鳗平时在进行游动的时候，会发出一些试探性的双脉冲或者三脉冲电压，以探测附近是否有生物存在。如果附近正好有一条可怜的小鱼，它可能就会被这个短暂的脉冲电压「惊得抖了一抖」，然而这马上就会被电鳗所感知，接下来电鳗就会对小鱼的方向使出高频高压的脉冲，直至把小鱼电得身体麻痹、无法动弹，此时便会只能任由电鳗的摆布，沦为盘中餐了。</p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/v2-67950bc08c57f55d48347e379da32d7f_b.png" alt="" /></p>
     * <blockquote>电鳗的试探性脉冲与攻击性脉冲（<em>Kenneth Catania. The shocking predatory strike of the electric eel. Science 346 (6214): 1231-1234</em>）</blockquote>
     * <p><img class="content-image" src="http://pic2.zhimg.com/v2-cd0bbe17da350026dc4fb77e37e6205d_b.png" alt="" /></p>
     * <blockquote>电鳗的试探性脉冲识别活物与死物（<em>Eckert&rsquo;s Animal Physiology (4th ed.)</em>）</blockquote>
     * <p>回到我们的皮卡丘，如果皮卡丘也是遵循着这个发电模式的话，恐怕需要的<strong>发电细胞量要更多</strong>，<strong>每个细胞上的离子泵也要更多</strong>，而且这些细胞也应该是像电鳗一样由肌肉细胞特化并且紧密状排列以节省更多的空间。</p>
     * <p>还有一点更加重要的是，电鳗等电鱼毕竟生活在河流、海洋等自然水环境中，而这些水的导电性很高（至少比空气中要高多了），所以电鳗的「八百伏特」才能够轻易地对外使出。然而皮卡丘是一个陆地宝可梦，它的绝招也多是在陆地环境中使出，所以其身体也必然要有一套防触电系统来保护自己在产生可以击穿空气的高电压时，不会伤害到自己。</p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/v2-93d904cbeb8740a20a5f742d6098c8a7_b.gif" alt="" /></p>
     * <p>（你说电鳗为什么不会伤害到自己？有两个方面的原因：一方面，电鳗身体的脂肪组织对它的整个身体起到了很好的保护和绝缘作用；另一方面，它们生活的水环境本身就是电的相对良导体，电鳗发出的电从尾部的正极出发，由于它整个身体表面绝缘性高，因此只能通过周围的液体环境，回到头部的负极。因此，电鳗发出的高压电并不会流过自己，也就产生不了什么伤害。）</p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/v2-366c91a1dd83b3ea32d3253417503b3b_b.jpg" alt="" /></p>
     * <p><img class="content-image" src="http://pic4.zhimg.com/v2-f46cc9180fb93fe5a5d022ab07b50047_b.jpg" alt="" /></p>
     * <p>其实文章的最后还是想告诉大家，「十万伏特」太太太高了，光靠生物电真的造不起来，所以皮卡丘真实的产电方式应该是这样的&hellip;&hellip;</p>
     * <p><img class="content-image" src="http://pic3.zhimg.com/v2-0c635b206149c40eb7f8709d5971d98e_b.jpg" alt="" /></p>
     * <p>
     * <div class="view-more"><a href="http://zhuanlan.zhihu.com/p/27484507">查看知乎讨论</a></div>
     * <p>
     * </div>
     * </div>
     * </div>
     * <p>
     * <p>
     * </div>
     * </div>
     * image_source : 《精灵宝可梦》
     * title : 为什么皮卡丘能放出十万伏特的电，而且不会电到自己？
     * image : https://pic1.zhimg.com/v2-b8f4f74d08e7355c57c9311dac8b7a3c.jpg
     * share_url : http://daily.zhihu.com/story/9486566
     * js : []
     * ga_prefix : 062115
     * images : ["https://pic2.zhimg.com/v2-7d781f56e25de6d5255c2085950458d9.jpg"]
     * type : 0
     * id : 9486566
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> js;
    private List<String> images;
    private List<String> css;
    private ThemeBean theme;

    public String getBody() {
        return body;
    }

    public String getImage_source() {
        return image_source;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<String> getJs() {
        return js;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getCss() {
        return css;
    }

    public ThemeBean getTheme() {
        return theme;
    }
}
