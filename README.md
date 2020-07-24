# MyFirstKotlin
Kotlinお試しプロジェクト。

## Activity
### BaseActivity
基底Activityのサンプル。
各Activityで共通の処理（ヘッダ表示など）を定義したい場合は基底Activityを作成してそこに処理を実装。

### MainActivity
アプリ起動して最初に動くActivity
画面遷移のサンプル。
各機能の一覧を表示する。

### OpenBrowserActivity
WebViewを使ってブラウザを埋め込むサンプル。

### UserManageActivity
DBアクセスに関するサンプル。
ユーザ情報を表示・登録・削除する。

- 更新ボタン
  - DBからデータを取得して画面のリスト表示を更新する。
- 追加ボタン
  - 指定した氏名でユーザ情報を登録する。
- 削除ボタン
  - ユーザをリストから長押しして選択すると削除ボタンが表示され、押下で物理削除する。
- 全削除ボタン
  - ユーザを全削除する。

### ViewListActivity
ファイル読み込みとListViewでリスト表示するサンプル。
TSVファイルを読み込んでリストに表示する。

### NothingActivity
ヘッダとボタンだけ配置した空のActivity。

## 画面構成
### header_menu.xml
ヘッダメニューのレイアウト。
### strings.xml
画面に表示する文言の定義。
### styles.xml
アプリの全体のテーマを定義。
### 
### 


