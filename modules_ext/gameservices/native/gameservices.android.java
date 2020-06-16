import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.common.GoogleApiAvailability;
import android.widget.Toast;

class GPgs {
  private Activity _activity;
  private Context _context;

  // Client used to sign in with Google APIs
  private GoogleSignInClient mGoogleSignInClient;

  // request codes useD when invoking an external activity
  private static final int RC_UNUSED = 5001;
  private static final int RC_SIGN_IN = 9001;
  private static final int RC_ACHIEVEMENT_UI = 9003;
  private static final int RC_LEADERBOARD_UI = 9004;

  public GPgs(){
    _activity=BBAndroidGame.AndroidGame().GetActivity();
    _context=_activity.getApplicationContext();
    mGoogleSignInClient = GoogleSignIn.getClient(_activity, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());
  }

  public void SignIn(Boolean popup) {
    if (!IsSignedIn()) {
      _activity.startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
      if (popup) { MakeToast("Signing in"); }
    }
    }	

  public int IsAvailable() {
    return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(_context);
  }

  public boolean IsSignedIn() {
    return GoogleSignIn.getLastSignedInAccount(_activity) != null;
  }

  private void MakeToast(final String message) {
    _activity.runOnUiThread(new Runnable() {
      public void run() {
        Toast.makeText(_activity, message, Toast.LENGTH_SHORT).show();
      }
    });
  }
  
  public void SignOut(Boolean popup) {
    if (IsSignedIn()) {
      mGoogleSignInClient.signOut();
      if (popup) { MakeToast("Signing in"); }
    }
  }


  public void SubmitLeaderboardScore(String leaderboard_id, Integer score, String popup_message) {
    if (IsSignedIn()) {
      Games.getLeaderboardsClient(_activity, GoogleSignIn.getLastSignedInAccount(_activity)).submitScore(leaderboard_id, score);
       if (popup_message!="") { MakeToast(popup_message); }
    }
  }

  public void ShowLeaderboard(String leaderboard_id) {
    if (IsSignedIn()) {
      Games.getLeaderboardsClient(_activity, GoogleSignIn.getLastSignedInAccount(_activity))
            .getLeaderboardIntent(leaderboard_id)
            .addOnSuccessListener(new OnSuccessListener<Intent>() {
                @Override
                public void onSuccess(Intent intent) {
                    _activity.startActivityForResult(intent, RC_LEADERBOARD_UI);
                }
            });
    }
  }

  public void UnlockAchievement(String achievement_id, String popup_message) {
    if (IsSignedIn()) {
      Games.getAchievementsClient(_activity, GoogleSignIn.getLastSignedInAccount(_activity)).unlock(achievement_id);
      if (popup_message!="") { MakeToast(popup_message); }
    }
  }

  public void ShowAchievements() {
    if (IsSignedIn()) {
        Games.getAchievementsClient(_activity, GoogleSignIn.getLastSignedInAccount(_activity))
                .getAchievementsIntent()
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        _activity.startActivityForResult(intent, RC_ACHIEVEMENT_UI);
                    }
                });
    }
  }
}
