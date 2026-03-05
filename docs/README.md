# Pepsi — Task Manager Chatbot

> _Smarter, clearer, and less overrated than Coke._

Pepsi is a command-line chatbot that helps you keep track of your tasks. It supports three types of tasks, persistent storage, and keyword search — all through simple text commands.

---

## Quick Start

**Prerequisites:** JDK 17

1. Run the application.
2. Pepsi will load your previously saved tasks automatically.
3. Start typing commands — see the full list below.

> [!TIP]
> Tasks are saved automatically after every change, so you never need to manually save.

---

## Features

### Add a To-Do
A basic task with no date.

```
todo <description>
```

**Example:**
```
todo buy groceries
```

---

### Add a Deadline
A task with a due date.

```
deadline <description> /by <date>
```

**Example:**
```
deadline submit assignment /by 2025-04-15
```

> [!IMPORTANT]
> The date **must** be in `yyyy-MM-dd` format (e.g. `2025-04-15`). Any other format will be rejected.

---

### Add an Event
A task spanning a time range.

```
event <description> /from <start> /to <end>
```

**Example:**
```
event team meeting /from Monday 2pm /to 4pm
```

---

### List All Tasks
Displays everything currently in your list.

```
list
```

---

### Mark a Task as Done

```
mark <task number>
```

**Example:**
```
mark 2
```

---

### Unmark a Task

```
unmark <task number>
```

---

### Delete a Task

```
delete <task number>
```

> [!NOTE]
> Use `list` first to confirm the task number before deleting.

---

### Find Tasks by Keyword
Searches all task descriptions. Case-insensitive.

```
find <keyword>
```

**Example:**
```
find assignment
```

---

### Exit

```
bye
```

---

## Understanding the Task Display

Each task is shown in this format:

```
[type][status] description
```

| Symbol | Meaning |
|--------|---------|
| `T` | Todo |
| `D` | Deadline |
| `E` | Event |
| `X` | Done |
| ` ` _(blank)_ | Not done |

**Example output from `list`:**
```
1. [T][ ] buy groceries
2. [D][X] submit assignment (by: Apr 15 2025)
3. [E][ ] team meeting (from: Monday 2pm to 4pm)
```

---

## Command Summary

| Command | Format |
|---------|--------|
| Add todo | `todo <description>` |
| Add deadline | `deadline <description> /by <yyyy-MM-dd>` |
| Add event | `event <description> /from <start> /to <end>` |
| List all | `list` |
| Mark done | `mark <number>` |
| Unmark | `unmark <number>` |
| Delete | `delete <number>` |
| Find | `find <keyword>` |
| Exit | `bye` |
