# MyFirstKotlin
Kotlinお試しプロジェクト。

## AndroidManifest.xml
アプリケーション全体に関する定義。  
使用するactivityをここに記載する必要がある。

## Activity
### BaseActivity
基底Activity。  
各Activityで共通の処理（ヘッダ表示など）をここに実装。

### MainActivity
アプリ起動して最初に動くActivity。  
画面遷移(他のActivityへの遷移)のサンプル。  
各機能のボタンを表示する。

### OpenBrowserActivity
WebViewを使ってブラウザを埋め込むサンプル。

### UserManageActivity
DBアクセスに関するサンプル。  
ユーザ情報を表示・登録・削除する。

- 初期表示
  - DBからユーザリストを取得して表示。
- 再表示ボタン
  - DBからデータを再取得して画面のリスト表示を更新する。
- 追加ボタン
  - 指定した氏名でユーザを登録する。
- 全削除ボタン
  - ダイアログが表示され、OKでユーザを全削除する。
- リストから各ユーザを長押し
  - ダイアログが表示され、OKで指定ユーザを削除する。

### ViewListActivity
ファイル読み込みとListViewでリスト表示するサンプル。  
固定TSVファイルを読み込んでリストに表示する。

### NothingActivity
ヘッダとボタンだけ配置した空のActivity。

## 画面構成
### 各レイアウトファイル
activity_main.xmlなど、res/layoutの下にあるファイル。画面上の部品のIDや位置など定義する。

### header_menu.xml
ヘッダメニューのレイアウト。
### strings.xml
画面に表示する文言の定義。
### styles.xml
アプリの全体のテーマを定義。

## DB関連
昔はSQLiteが主流だったらしいが、今はRoomというローカルDBが主流らしいのでそれを使用。  
Android公式サイトもRoomを使用することを強く推奨している。https://developer.android.com/training/data-storage/room?hl=ja
### AppDatabase(アノテーション：@Database)
Roomデータベースにアクセスするためのアクセスポイント。
### UserDao(アノテーション：@Dao)
Userエンティティに関するクエリを定義する。
### User(アノテーション：@Entity)
Userエンティティの定義(PKやカラム名など)。


