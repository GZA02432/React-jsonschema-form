db = db.getSiblingDB('admin');
db.auth('root','example');
db = db.getSiblingDB('formdb');
db.formschema.insertMany([
   { 
    formName: 'A list of tasks',
    formDescription: 'string',
    formSchema: {
        "title": "A list of tasks",
        "type": "object",
        "required": [
          "title"
        ],
        "properties": {
          "title": {
            "type": "string",
            "title": "Task list title"
          },
          "tasks": {
            "type": "array",
            "title": "Tasks",
            "items": {
              "type": "object",
              "required": [
                "title"
              ],
              "properties": {
                "title": {
                  "type": "string",
                  "title": "Title",
                  "description": "A sample title"
                },
                "details": {
                  "type": "string",
                  "title": "Task details",
                  "description": "Enter the task details"
                },
                "done": {
                  "type": "boolean",
                  "title": "Done?",
                  "default": false
                }
              }
            }
          }
        }
      },
    uiSchema: {
        "tasks": {
          "items": {
            "details": {
              "ui:widget": "textarea"
            }
          }
        }
      }
   }
]);