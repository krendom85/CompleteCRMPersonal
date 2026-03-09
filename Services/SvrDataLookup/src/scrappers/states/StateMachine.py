from sre_parse import State
from typing import Any, Dict, List


class StateMachine:
    def __init__(self, states: List[State]):
        self.states = states


    def run(self, context: Dict[str, Any]) -> Dict[str, Any]:
        for state in self.states:
            context = state.execute(context)
        return context