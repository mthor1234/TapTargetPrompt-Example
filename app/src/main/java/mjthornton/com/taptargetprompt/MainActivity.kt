package mjthornton.com.taptargetprompt

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFabPrompt()

    }

    private fun showFabPrompt() {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(this)

        if (!preferenceManager.getBoolean("didShowPrompt", false)) {
            MaterialTapTargetPrompt.Builder(this)
                    .setTarget(R.id.fab)
                    .setPrimaryText("Click Me!")
                    .setSecondaryText("IM a FAb click me!")
                    .setBackButtonDismissEnabled(true)
                    .setPromptStateChangeListener { prompt, state ->
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED
                                || state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED) {
                            val prefEditor = preferenceManager.edit()
                            prefEditor.putBoolean("did show prompt", true)
                            prefEditor.apply()

                            showButtonPrompt()
                        }
                    }
                    .show()
        }
    }

    private fun showButtonPrompt() {
        MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.button)
                .setPrimaryText("Press Me!")
                .setSecondaryText("IM a Button click me!")
                .setBackButtonDismissEnabled(true)
                .setPromptBackground(RectanglePromptBackground())
                .setPromptFocal(RectanglePromptFocal())
    }
}

