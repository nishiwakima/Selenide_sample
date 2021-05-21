import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class Selenide_amazontest {
    @Test
    public void test() {
        // amazonトップページを開く
        open("https://www.amazon.co.jp/");

        // 検索エリアのidを取得して"商品名"を入力後、Enterキーを押下
        $(byId("twotabsearchtextbox")).val("ねるねるねるね").pressEnter();

        // 現在開いているのタブのハンドルを取得
        String Handle = getWebDriver().getWindowHandle();

        // 商品のリンクテキストを取得してクリック
        $(By.linkText("ねるねるねるね ソーダ味 10個入 食玩・知育菓子")).click();

        // 新規タブのハンドルを用意して、タブが開かれたらnewHandleに代入
        String newHandle = null;
        for(String id : getWebDriver().getWindowHandles()){
            if(!id.equals(Handle)){
                newHandle = id;
            }
        }

        // 新しいタブにハンドルを移動
        getWebDriver().switchTo().window(newHandle);

        // カートに追加ボタンのidを取得してクリック
        $(byId("add-to-cart-button")).click();

        // カートアイコンのidを取得してクリック
        $(byId("nav-cart")).click();

        /*
         * 商品名の確認
         */
        $(By.cssSelector("span[class='a-size-medium sc-product-title']")).shouldBe(text("ねるねるねるね ソーダ味"));

        /*
         * 商品価格の確認
         */
        $(By.cssSelector("span[class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")).shouldBe(text("￥1,296"));

        // 削除ボタンをCSSセレクタで取得してクリック
        $(By.cssSelector("span[class='a-declarative']")).click();

    }
}
