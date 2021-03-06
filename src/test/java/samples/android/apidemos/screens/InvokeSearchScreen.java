package samples.android.apidemos.screens;

import aquality.appium.elements.interfaces.IButton;
import aquality.appium.elements.interfaces.ILabel;
import aquality.appium.elements.interfaces.ITextBox;
import aquality.appium.screens.AndroidScreen;
import org.openqa.selenium.By;

public class InvokeSearchScreen extends AndroidScreen {

    private final ITextBox txbSearch = getElementFactory().getTextBox(By.id("txt_query_prefill"), "Search");
    private final IButton btnStartSearch = getElementFactory().getButton(By.id("btn_start_search"), "Start search");
    private final ILabel lblSearchResult = getElementFactory().getLabel(By.id("android:id/search_src_text"), "Search results");

    public InvokeSearchScreen() {
        super(By.xpath("//*[contains(.,'App/Search/Invoke Search')]"), "Invoke Search");
    }

    public void submitSearch(String query) {
        txbSearch.clearAndType(query);
        btnStartSearch.click();
    }

    public String getSearchResult() {
        return lblSearchResult.getText();
    }
}
