package cc.changliu.codelabs.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridLayoutItemDecoration(context: Context?, hDrawableResId: Int, vDrawableResId: Int) :
    ItemDecoration() {
    private val hDivider = AppCompatResources.getDrawable(context!!, hDrawableResId)
    private val vDivider = AppCompatResources.getDrawable(context!!, vDrawableResId)

    private val hWidth = DEFAULT_WIDTH
    private val vWidth = DEFAULT_WIDTH

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        Log.e("GridLayoutItemDecoration", "onDraw invoked")
        drawHeaderBackground(c, parent)
        drawHorizontal(c, parent)
        drawVertical(c, parent)
    }

    private fun drawHeaderBackground(c: Canvas, parent: RecyclerView) {
//        var i = 0
//        while (i < getSpanCount(parent)) {
//            val child = parent[i]
//            child.setBackgroundResource(android.R.color.background_dark)
//            child.
//        }
    }

    private fun getSpanCount(parent: RecyclerView): Int {
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            return layoutManager.spanCount
        }
        return 0
    }

    fun drawHorizontal(c: Canvas?, parent: RecyclerView) {
        val childCount = parent.childCount
        val spanCount = getSpanCount(parent)
        var i = 0
        while (i < childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = parent.left
            val right = parent.right
            val top = child.bottom + params.bottomMargin
            val bottom = top + hWidth
            hDivider!!.setBounds(left, top, right, bottom)
            hDivider.draw(c!!)
            i += spanCount
        }
    }

    fun drawVertical(c: Canvas?, parent: RecyclerView) {
        val spanCount = getSpanCount(parent)
        for (i in 0 until spanCount) {
            val child = parent.getChildAt(i) ?: continue

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = parent.top
            val bottom = parent.getChildAt(parent.childCount - 1).bottom
            val left = child.right + params.rightMargin
            val right = left + vWidth

            vDivider!!.setBounds(left, top, right, bottom)
            vDivider.draw(c!!)
        }
    }

    private fun isLastColumn(
        parent: RecyclerView,
        pos: Int,
        spanCount: Int,
        childCount: Int
    ): Boolean {
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            return (pos + 1) % spanCount == 0
        }
        return false
    }

    private fun isLastRaw(
        parent: RecyclerView,
        pos: Int,
        spanCount: Int,
        childCount: Int
    ): Boolean {
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            val completeRows = childCount / spanCount
            val itemsInLastRow = childCount % spanCount

            // 如果是最后一行，并且这一行是完整的或者位置超过了完整的行数
            return pos >= completeRows * spanCount && (itemsInLastRow == 0 || pos < (completeRows + 1) * spanCount)
        }
        return false
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val spanCount = getSpanCount(parent)
        val itemCount = parent.adapter!!.itemCount
        if (isLastRaw(parent, itemPosition, spanCount, itemCount)) {
            // 如果是最后一行，则不需要绘制底部
            outRect[0, 0, vWidth] = 0
        } else if (isLastColumn(parent, itemPosition, spanCount, itemCount)) {
            // 如果是最后一列，则不需要绘制右边
            outRect[0, 0, 0] = hWidth
        } else {
            outRect[0, 0, vWidth] = hWidth
        }
    }

    companion object {
        private const val DEFAULT_WIDTH = 1 // PX
    }
}
