package com.applikable.Schools.NewsAndActivities;

/**
 * Created by Hashim on 02/02/2015.
 */


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.SchoolParser;


@SuppressLint("CutPasteId")
public class ClickedNews extends FragmentActivity {
    String Type;
    private WebView webView;
    private ActionBar actionBar;
    private TextView txthead;
    private ImageView shareImg;
    private String Url;
    private ImageView samsung;
    private ProgressDialog mProgressDialog;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        webView = (WebView) findViewById(R.id.webView1);

        TextView title = (TextView) findViewById(R.id.textView11);
        Type = (getIntent().getStringExtra("news"));
        webView.getSettings().setJavaScriptEnabled(true);
        // for multitoutches
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        // to set the view in default size
        final String partial[] = new String[3];
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        mProgressDialog = new ProgressDialog(ClickedNews.this);

        Url = Type;
        mAsync mAsync = new mAsync();
        if (Type.equals("1")) {
            DataBase db = new DataBase(this);
            db.getSchedDetail();
            Url = db.getSchedDetail().getStudyPlan();
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

            webView.setInitialScale(205);

            mAsync.execute();

        } else if (Type.equals("2")) {
            DataBase db = new DataBase(this);
            db.getSchedDetail();
            Url = db.getSchedDetail().getGenNote();
            webView.setInitialScale(180);

            mAsync.execute();

        } else {


            try {

//                        partial[1] = SchoolParser.getPartialPage(ClickedNews.this, Url);
                Url = Type;
                mAsync.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.tab1));
        // for the action bar
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("التفاصيل");

        if (Type.equals("1"))
            actionBar.setTitle("StudyPlan");
        if (Type.equals("2"))
            actionBar.setTitle("ملاحظات عامة");
        if (Type.contains("aspx"))
            actionBar.setTitle("التفاصيل");

        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#5E6F13")));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    public void open(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
                .parse(Url));
        startActivity(browserIntent);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class mAsync extends AsyncTask<Void, Void, Void> {
        String html;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(ClickedNews.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (Url.contains("node")) {


                webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
                mProgressDialog.dismiss();


            } else if (Url.contains("null") || Url.isEmpty()) {
//                android.support.v4.app.FragmentManager fm = ClickedNews.this.getSupportFragmentManager();
//
//                UniDialog u = new UniDialog("لا يوجد ملاحظات");
//
//                u.show(fm, "fragment_edit_name");


                Toast.makeText(ClickedNews.this, "لا يوجد ملاحظات", Toast.LENGTH_SHORT).show();

                finish();


            } else {
                webView.loadDataWithBaseURL(null, Url, "text/html", "utf-8", null);
            }
            mProgressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            html = SchoolParser.getPartialPage(ClickedNews.this, Url).replace("src=\"", "src=\"http://www.ipas-edu.com/");


            return null;
        }
    }

}


// private TextView txtViewTitle, wordsTxtView;
// ArrayList<SetGet> wordsArray, newsList, clickedNews;
// String imgUrl, titleUrl;
// ImageView imgViewItem;
// Context context;
// int postion;
// View animeImgView, animeTxtViewWords, animeTxtTile;
// private ActionBar actionBar;
// private TextView txthead;
// private ImageView shareImg;
// private ImageView samsung;
// private String title;
// DataBaseMangment db1;
// private HttpURLConnection conn;
// private DataBaseMangment data;
// private ScrollView layout;
//
// @SuppressLint({ "CutPasteId", "NewApi" })
// @Override
// protected void onCreate(Bundle savedInstanceState) {
// // TODO Auto-generated method stub
// super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_clicked_news);
//
// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
// int firstColor = 0xff034F8D;
// int secondColor = 0xff1F1F1F;
// int[] colorArray = { firstColor, secondColor, firstColor, secondColor,
// firstColor };
// // views
// layout = (ScrollView) findViewById(R.id.svNews);
// layout.setBackground(new ColorDrawable(MethodToUse.Firstcolor()));
// txtViewTitle = (TextView) findViewById(R.id.tvTitle);
// wordsTxtView = (TextView) findViewById(R.id.tvN9);// ظ†طµ
// imgViewItem = (ImageView) findViewById(R.id.ivClickedNews);
// animeImgView = findViewById(R.id.ivClickedNews);
// animeTxtViewWords = findViewById(R.id.tvN9);
// txtViewTitle.setTypeface(MethodToUse.FontTypeFace_3D(this));
// wordsTxtView.setTypeface(MethodToUse.FontTypeFace_3D(this));
// animeTxtTile = findViewById(R.id.tvTitle);
//
// //
// wordsArray = new ArrayList<SetGet>();
// context = this;
// DataBaseMangment db = new DataBaseMangment(context);
// newsList = db.getAllNews();
//
// // intent extra
// String postionString = getIntent().getStringExtra("postion");
// imgUrl = getIntent().getStringExtra("imgUrl");
// titleUrl = getIntent().getStringExtra("titleUrl");
// title = getIntent().getStringExtra("title");
// postion = Integer.parseInt(postionString);
//
// // to check the data base
//
// try {
// goCheck(postion);
// } catch (Exception e) {
//
// }
//
// // for the action bar
// actionBar = getSupportActionBar();
// // actionBar.setIcon(R.drawable.meu_logo_small);
// actionBar.setDisplayShowCustomEnabled(true);
// actionBar.setDisplayShowHomeEnabled(false);
// actionBar.setCustomView(R.layout.action_bar_sahre);
// txthead = (TextView) findViewById(R.id.ActionbarTitle);
// txthead.setText("تفاصيل الخبر");
// txthead.setTextColor(MethodToUse.Textcolor());
// txthead.setGravity(Gravity.CENTER);
// txthead.setTypeface(MethodToUse.FontTypeFace_XXX(this));
// shareImg = (ImageView) findViewById(R.id.ivShare);
// samsung = (ImageView) findViewById(R.id.ivSamsung);
//
// data = new DataBaseMangment(context);
//
// shareImg.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
//
// Intent share = new Intent(Intent.ACTION_SEND);
// share.putExtra(Intent.EXTRA_TEXT, titleUrl);
// share.setType("text/plain");
// startActivity(Intent.createChooser(share,
// "Share Event via BAUApp: "));
// }
// });
// samsung.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
//
// Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
// .parse("http://www.samsung.com/"));
// startActivity(browserIntent);
// }
// });
//
// }
//
// private void goCheck(int postion) {
// DataBaseMangment db1 = new DataBaseMangment(context);
// int check = db1.getClickedNewsCount();
//
// if (check == 0) {
// ClickedAsync obj1 = new ClickedAsync();
// obj1.execute();
// } else {
//
// clickedNews = db1.getAllClickedNews();
// txtViewTitle.setText(clickedNews.get(postion).getClickedTitle());
// txtViewTitle.setTextColor(MethodToUse.Textcolor());
// MethodToUse obj = new MethodToUse();
// ImageLoader.getInstance().init(obj.imgLoaderOption(context));
// ImageLoader.getInstance().displayImage(imgUrl, imgViewItem);
// wordsTxtView.setText(clickedNews.get(postion).getClickdWords());
// // animation
// wordsTxtView.setTextColor(MethodToUse.Textcolor());
// Animation anime = AnimationUtils
// .makeInChildBottomAnimation(context);
// animeImgView.startAnimation(anime);
// Animation anime1 = AnimationUtils.makeInAnimation(context, false);
// animeTxtViewWords.startAnimation(anime1);
// Animation anime11 = AnimationUtils.makeInAnimation(context, true);
// animeTxtTile.startAnimation(anime11);
// }
//
// }
//
// public class ClickedAsync extends AsyncTask<Void, Void, Void> {
// Document doc;
// String words = "";
// private ProgressDialog dialog;
// private boolean error = false;
// private String date;
//
// @Override
// protected void onPreExecute() {
// super.onPreExecute();
// dialog = new ProgressDialog(context);
// dialog.setMessage("Loading...");
// dialog.setIndeterminate(false);
// dialog.setCancelable(false);
// dialog.show();
//
// }
//
// protected Void doInBackground(Void... params) {
//
// try {
//
// bringClickedNews();
//
// } catch (IOException e) {
//
// error = true;
// e.printStackTrace();
// } catch (Exception e) {
// error = true;
// e.printStackTrace();
// }
// return null;
// }
//
// private void bringClickedNews() throws Exception {
// String title = "";
// String newLine = "\n";
// for (int i = 0; i < newsList.size(); i++) {
//
// doc = Jsoup.parse(sendGet(newsList.get(i).getTitlesUrl()));
//
// words="";
// Elements td_s = doc
// .select("#WebPartWPQ2 > table > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(3) > td > div").first().children()
// ;
// if(doc.title().contains("Open Educational Resources in Jordan:")){
// // to the follower developer i had to use this bnd8a cause this specific news
// has a different structure sorry for that and fuck QA :D
// words =getResources().getString(R.string.MFQ_Bnd8a);
// words = words.replace(".", "\n");
// data.addclickedNews(doc.title(), words);
// continue;
//
// }
//
// if(doc.title().contains("President Meets with Canadian Embassy Delegation")){
// // to the follower developer i had to use this bnd8a cause this specific news
// has a different structure sorry for that and fuck QA :D
// words =getResources().getString(R.string.MFQ_Band8a2);
// words = words.replace(".", "\n");
// data.addclickedNews(doc.title(), words);
// continue;
//
// }
// for (Element element : td_s) {
// Elements news = element.children();
// for (Element element2 : news) {
// if(element2.tagName().equals("span"))
// words = words+element2.text()+newLine;
//
// if(element2.tagName().equals("b"))
// words = words+element2.text();
//
// System.out.println(words);
// }
// }
// title = doc.title();
// if (title.contains("JustNews -"))
// title = title.replace("JustNews -", "");
// if (title.contains("..."))
// title = title.replace("...", "");
//
// data.addclickedNews(title, words);
// }
//
// }
//
// protected void onPostExecute(Void result) {
//
// if (!error) {
// DataBaseMangment db1 = new DataBaseMangment(context);
// clickedNews = db1.getAllClickedNews();
// txtViewTitle
// .setText(clickedNews.get(postion).getClickedTitle());
// txtViewTitle.setTextColor(MethodToUse.Textcolor());
// MethodToUse obj = new MethodToUse();
// ImageLoader.getInstance().init(obj.imgLoaderOption(context));
// ImageLoader.getInstance().displayImage(imgUrl, imgViewItem);
// wordsTxtView.setText(clickedNews.get(postion).getClickdWords());
// wordsTxtView.setTextColor(MethodToUse.Textcolor());
// Animation anime = AnimationUtils
// .makeInChildBottomAnimation(context);
// animeImgView.startAnimation(anime);
// Animation anime1 = AnimationUtils.makeInAnimation(context,
// false);
// animeTxtViewWords.startAnimation(anime1);
// Animation anime11 = AnimationUtils.makeInAnimation(context,
// true);
// animeTxtTile.startAnimation(anime11);
// } else {
// MethodToUse.tostCreator(context,
// StringClass.Error_In_INTERNAET_CONNECTION);
// db1.deleteClickedNewsTable();
// }
//
// dialog.dismiss();
// }
//
// }
//
// private String sendGet(String url) throws Exception {
// URL obj = new URL(url);
// conn = (HttpURLConnection) obj.openConnection();
// conn.setChunkedStreamingMode(0);
// conn.setRequestMethod("GET");
// conn.setUseCaches(false);
// conn.setRequestProperty("Host", "www.just.edu.jo");
// conn.setRequestProperty("User-Agent", StringClass.USER_AGENT);
//
// int responseCode = conn.getResponseCode();
// System.out.println("\nSending 'GET' request to URL : " + url);
// System.out.println("Response Code here : " + responseCode);
//
// BufferedReader in = new BufferedReader(new InputStreamReader(
// conn.getInputStream(), "UTF-8"));
// String inputLine;
// StringBuffer response = new StringBuffer();
//
// while ((inputLine = in.readLine()) != null) {
// response.append(inputLine);
//
// }
// in.close();
//
// return response.toString();
// }
//
// }
