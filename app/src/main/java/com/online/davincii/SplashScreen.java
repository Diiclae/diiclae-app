To add a fade-in animation to your `SplashScreen` activity, you can use the Android animation framework. You will create a fade-in animation resource and apply it to the root view of your splash screen layout when the activity is created.

First, create an animation resource file in the `res/anim` directory. If it doesn't already exist, you will need to create the `anim` directory.

1. Create a new directory named `anim` under `res`.

2. Inside the `anim` directory, create a new XML file named `fade_in.xml`.

3. Add the following content to `fade_in.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000"
    android:fromAlpha="0.0"
    android:toAlpha="1.0" />

```

This creates a simple fade-in animation that lasts for 1000 milliseconds (1 second).

Next, you need to apply this animation to your activity's root view in the `SplashScreen` class.

4. Modify your `onCreate` method in the `SplashScreen` class like this:

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    // Apply fade-in animation to the root view
    View rootView = findViewById(android.R.id.content);
    Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
    rootView.startAnimation(fadeIn);

    Window window = this.getWindow();
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

    context = SplashScreen.this;
    apiInterface = ApiClient.getClient();
    progress = new GlobalProgressDialog(context);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent;
            if (BaseUtil.getUserLogIn(SplashScreen.this)) {
                intent = new Intent(SplashScreen.this, DashboardScreen.class);
                startActivity(intent);
            } else {
                intent = new Intent(SplashScreen.this, UserLogin.class);
                startActivity(intent);
            }
            SplashScreen.this.finish();
        }
    }, 3500);
}
```

This will cause the entire splash screen to fade in when the activity is launched, providing a smooth transition experience for the user. The fade-in animation is set to occur over one second as specified in the `fade_in.xml` animation resource. Adjust the `android:duration` value to fit your preference if necessary.