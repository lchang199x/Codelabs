package designpattern

interface IServiceService: IProvider {
    fun setSearchStrategy(strategy: SearchStrategy)
}

// 维护全局信息，定义client感兴趣的接口，持有Strategy的引用，并将request转发给strategy
class LotusSearchContext(strategy: SearchStrategy) {
    fun searchByKeyword(keyword: String, callback: SearchCallback) {

    }
    fun searchAround(center: POI, range: Int, callback: SearchCallback) {

    }
    fun searchAlongTheWay(category: String, route: Route, callback: SearchCallback) {

    }
}

// Abstract strategy
interface SearchStrategy {
    fun searchByKeyword(keyword: String, callback: SearchCallback)
    fun searchAround(center: POI, range: Int, callback: SearchCallback)
    fun searchAlongTheWay(category: String, route: Route, callback: SearchCallback)
}

// Concrete strategy AMap
class AMapSearchStrategy: SearchStrategy {
    override fun searchByKeyword(keyword: String, callback: SearchCallback) {
    }

    override fun searchAround(center: POI, range: Int, callback: SearchCallback) {
    }

    override fun searchAlongTheWay(category: String, route: Route, callback: SearchCallback) {
    }

}

// Concrete strategy Baidu
class BaiduSearchStrategy: SearchStrategy {
    override fun searchByKeyword(keyword: String, callback: SearchCallback) {
    }

    override fun searchAround(center: POI, range: Int, callback: SearchCallback) {
    }

    override fun searchAlongTheWay(category: String, route: Route, callback: SearchCallback) {
    }

}

class POI

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Route(val path: String)

interface IProvider {
    fun init()
}
interface SearchCallback {
    fun onResult(result: List<POI>)
}

class ARouter {
    fun <T> navigation(cls: Class<T>): T? {
        return null
    }

    companion object {
        fun getInstance(): ARouter {
            return ARouter()
        }
    }
}

@Route(path = "/router/search")
class ServiceServiceImpl(var strategy: SearchStrategy = AMapSearchStrategy()): SearchStrategy by strategy, IServiceService {
    override fun setSearchStrategy(strategy: SearchStrategy) {
        this@ServiceServiceImpl.strategy = strategy
    }

    override fun init() {
    }

}

// Client，实例化context和strategy,发送request给context
fun main() {
    val aMapSearchStrategy = AMapSearchStrategy()
    val baiduSearchStrategy = BaiduSearchStrategy()

    val impl = ARouter.getInstance().navigation(IServiceService::class.java)

    val context = LotusSearchContext(aMapSearchStrategy)

    context.searchByKeyword("lotus", object : SearchCallback {
        override fun onResult(result: List<POI>) {
        }
    })
}