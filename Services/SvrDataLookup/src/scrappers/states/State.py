from abc import ABC, abstractmethod
from typing import Any, Dict

class State(ABC):
    @abstractmethod
    def execute(self, context: Dict[str, Any]) -> Dict[str, Any]:
        pass

    