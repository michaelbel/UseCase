UseCase
=

[![workflow-status](https://img.shields.io/github/actions/workflow/status/michaelbel/usecase/ci.yml?style=for-the-badge&logo=github&labelColor=3F464F)](https://github.com/michaelbel/usecase/actions)
[![last-commit](https://img.shields.io/github/last-commit/michaelbel/usecase?style=for-the-badge&logo=github&labelColor=3F464F)](https://github.com/michaelbel/usecase/commits)

Практическое MVI-демо использования `UseCase` и `FlowUseCase`: базовые классы перенесены из `vpdelivery-android/shared/domain` без изменения поведения.

- `UseCase` переключает dispatcher, выполняет suspend-операцию и возвращает `Result<R>`;
- `FlowUseCase` применяет dispatcher к потоку через `flowOn`.

## Сценарий

1. `DemoTasksFlowUseCase(Unit)` постоянно наблюдает за in-memory источником и обновляет список через `collectLatest`.
2. `AddTaskUseCase(Unit).getOrThrow()` имитирует одноразовую IO-операцию и добавляет задачу.
3. `ToggleTaskUseCase(taskId).getOrThrow()` изменяет состояние выбранной задачи.
4. Изменение источника автоматически приходит в UI через `FlowUseCase`, MVI model и `ListItem`.

```text
UI Intent → ViewModel → UseCase → InMemoryTaskDataSource
                         ↓
                    FlowUseCase
                         ↓
                    MVI Model → ListItem UI
```

## Модули

Проект состоит из единственного модуля `app`. Внутри него, в пакете `org.michaelbel.usecase.shared`, собраны:

- `shared/mvi` — базовые MVI-классы (`MviViewModel`, `CoroutineViewModel`, `Intent`, `Model`, `Event`);
- `shared/domain` — `UseCase`, `FlowUseCase` и доменная модель;
- `shared/coroutines` — `SharedDispatchers` из архитектуры `vpdelivery`;
- `shared/data` — демонстрационный in-memory data source.

## Сборка

```shell
./gradlew :app:assembleDebug
```

