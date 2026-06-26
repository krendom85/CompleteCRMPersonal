package commands

type ProcessInfo interface {
	Validate(data string) (bool, error)
	Exists(data string) (bool, error)
	Execute(data string) (string, error)
	Save(data string) error
}
