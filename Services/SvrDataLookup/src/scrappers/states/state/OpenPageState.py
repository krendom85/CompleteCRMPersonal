from src.scrappers.states import State


class OpenPageState(State):
    def __init__(self, url:str):
        self.url = url

    def execute(self, context):
        driver = context["driver"]
        driver.get(self.url)
        return context

