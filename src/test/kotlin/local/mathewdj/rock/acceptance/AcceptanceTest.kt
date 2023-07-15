package local.mathewdj.rock.acceptance

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty, summary, html:build/reports/tests/cucumber.html, json:build/reports/tests/cucumber.json",
)
@ConfigurationParameter(
        key = io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME,
        value = "not @under-development"
)
class AcceptanceTest
