from src.scrappers.states import State

class FillFormState(State):
    def __init__(self, field_id: str, value: str):
        self.field_id = field_id
        self.value = value

    def execute(self, context):
        driver = context["driver"]
        driver.find_element("id", self.field_id).send_keys(self.value)
        return context