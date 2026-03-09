from src.scrappers.states import State


class ClickButtonState(State):
    def __init__(self, button_id: str):
        self.button_id = button_id

    def execute(self, context):
        driver = context["driver"]
        driver.find_element("id", self.button_id).click()
        return context