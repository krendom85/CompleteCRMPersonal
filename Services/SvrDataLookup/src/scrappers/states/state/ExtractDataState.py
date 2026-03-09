from src.scrappers.states import State

class ExtractDataState(State):
    def __init__(self, data_id: str):
        self.data_id = data_id

    def execute(self, context):
        driver = context["driver"]
        context["data"] = driver.find_element("id", self.data_id).text
        return context