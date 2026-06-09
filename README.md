Revision app done
1. App Setup & Layout Binding (Image 1)
This section prepares the screen and connects the visual elements (XML) to the logical code (Kotlin).
package com.example.revision The unique ID and folder structure for your app on the Android device.
import ... Pulls in required Android platform features (like Button for clicking, EditText for typing, and Log for hidden console messages).
class MainActivity : AppCompatActivity() { Creates your main screen. AppCompatActivity ensures your app looks and works consistently on both older and newer versions of Android.
override fun onCreate(savedInstanceState: Bundle?) { The entry point of the screen. This function runs automatically the exact millisecond the app opens.
enableEdgeToEdge() Tells the layout to extend fully to the absolute edges of the physical screen, drawing behind the status and navigation bars.
setContentView(R.layout.activity_main) Infuses your Kotlin code with your XML visual layout (activity_main.xml). Without this, the screen would just be entirely blank.
ViewCompat.setOnApplyWindowInsetsListener(...) { ... } An automatic system tool that calculates how thick the phone's status/notch bar and bottom navigation bar are (systemBars), adding invisible padding to your layout so your app's buttons don't get cut off by physical screen cutouts.
val edtUsername = findViewById<EditText>(R.id.edtUsername) (edtUserMax, edtUserMin, edtRandomNumber, and btnContinue follow this exact same pattern) This looks at your XML layout file, finds the specific visual element matching that exact ID name, and saves it into a Kotlin variable so you can manipulate it.
2. Button Action & Reading Input (Image 2)
This block sets up the action trigger and grabs what the user typed.
btnContinue.setOnClickListener { Creates a listener loop. The app will quietly sit there doing nothing until the user physically taps the btnContinue button, which immediately triggers all code nested inside these brackets.
Log.d("btnContinue", "Button pressed") Prints a hidden diagnostic note to the developer's computer screen console (Logcat) so you can verify the button click actually registered.
val username = edtUsername.text.toString() (minText and maxText do the same) Goes into the input field box, extracts the editable text object (.text), converts it into a standard, clean text string (.toString()), and saves it into a temporary variable.
3. Data Validation & Safety Checking (Images 2 & 3)
This section acts as a shield, running step-by-step checks to halt execution before invalid data can break the math engine.
if (username.isEmpty()) { ... } (minText.isEmpty() and maxText.isEmpty() do the same) Checks if the text string is entirely blank (contains 0 characters).
edtUsername.error = "Empty field" An built-in Android UI feature. It turns the input field red and pops up a small warning icon containing this specific text error message to alert the user.
return@setOnClickListener An immediate emergency brake. It kicks the program completely out of the button click function right then and there, preventing any of the code below it from executing.
val userMin = minText.toIntOrNull() (userMax does the same) Attempts to convert the string text (like "42") into a mathematical integer number (42). If the user managed to type text like "abc", instead of crashing the app, it safely outputs a value of null.
if (userMin == null) { ... } Checks if the step right above resulted in a failure (null). If it did, it sets a warning error on the field and triggers the emergency brake.
4. Range Logic & Random Generation (Image 4)
The final section performs the core business math and outputs the results back to the device screen.
if (userMin >= userMax) { ... } Checks if the user accidentally inverted their parameters (e.g., Min: 50, Max: 10). Because a random range cannot calculate backwards like this, it flags an error and stops.
val generatedNum = Random.nextInt(userMin, userMax + 1) Asks the computer's internal clock engine to select an unpredictable number. The structure of Random.nextInt(start, end) is exclusive of the end number. By mathematically writing userMax + 1, you shift the boundary upward, making the user's maximum number a valid target to hit.
edtRandomNumber.setText(generatedNum.toString()) Takes the newly minted mathematical integer result, converts it back into regular display text, and forces it right into the display field box so the user can see it on their screen.
Log.d("MainActivity", "User: $username, Generated: $generatedNum") Uses string templates ($) to print one final confirmation log to the developer console summarizing exactly who pressed the button and what number they received.
