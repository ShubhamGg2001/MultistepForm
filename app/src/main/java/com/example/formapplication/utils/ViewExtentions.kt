import android.app.Activity
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.formapplication.R

fun Activity.addDots(currentPage: Int, item: Int, linerLayout: LinearLayout) {
    val dots = arrayOfNulls<ImageView>(item)
    linerLayout.removeAllViews()
    for (i in 0 until item) {
        dots[i] = ImageView(this)
        if (i == 0) {
            dots[i]?.setPadding(0, 0, 0, 0)
        } else {
            dots[i]?.setPadding(40, 0, 0, 0)
        }

        linerLayout.addView(dots[i])
    }
    if (dots.isNotEmpty()) {
        dots[currentPage]?.setImageResource(R.drawable.dot_fill_black)
    }
}