from abc import ABC,abstractmethod


class BaseScraper(ABC):
    @abstractmethod
    def get_info(self, data:str):
        pass