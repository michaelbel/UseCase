UseCase
=

[![workflow-status](https://img.shields.io/github/actions/workflow/status/michaelbel/usecase/ci.yml?style=for-the-badge&logo=github&labelColor=3F464F)](https://github.com/michaelbel/usecase/actions)
[![last-commit](https://img.shields.io/github/last-commit/michaelbel/usecase?style=for-the-badge&logo=github&labelColor=3F464F)](https://github.com/michaelbel/usecase/commits)

Практическое демо использования `UseCase` и `FlowUseCase`
- `UseCase` переключает dispatcher, выполняет suspend-операцию и возвращает `Result<R>`;
- `FlowUseCase` применяет dispatcher к потоку через `flowOn`.
