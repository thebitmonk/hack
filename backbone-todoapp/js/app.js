
    var Task = Backbone.Model.extend({
    });

    //define directory collection
    var TaskList = Backbone.Collection.extend({
        model: Task
    });

    var TaskView = Backbone.View.extend({
        tagName: "article",
        className: "task-container",
        template: _.template($("#taskTemplate").html()),
        editTemplate: _.template($("#editTemplate").html()),

        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        renderEdit: function(){
            this.$el.html(this.editTemplate(this.model.toJSON()));
            return this;
        }, 
        events: {
            "click div.delete": "deleteTask",
            "dblclick div": "editTask",
            "keypress input": "updateOnEnter",
            "blur input": "closeTask",
        },
        deleteTask: function(){
            this.model.destroy();
            this.remove();
        },
        editTask: function(){
            this.renderEdit();
            this.el.getElementsByTagName('input')[0].focus();
        },
        updateOnEnter: function(e){
            console.log('Update on Enter clicked');
            if(e.keyCode == 13)
                this.closeTask();
        },
        closeTask: function(){
            console.log('Called from blur');
            var task = this.el.getElementsByTagName('input')[0].value;
            this.model.set("task",task);
            this.render();
        }
    });

    //define master view
    var TaskListView = Backbone.View.extend({
        
        el: $('.tasks'),
        initialize: function (attributes, options) {
            this.attribute = options.attribute;
            this.tasks = options.tasks;
            this.collection = new TaskList(options.tasks);
            console.log(options.tasks);
            this.el = $('.tasks')[this.attribute];
            this.collection.on("add", this.renderTask, this);
            this.render();
        },

        render: function () {
            _.each(this.collection.models, function (item) {
                this.renderTask(item);
            }, this);
        },

        renderTask: function (item) {
            console.log('Render Task called');
            console.log(JSON.stringify(item));
            
            var taskView = new TaskView({
                model: item
            });
            console.log(taskView.render().el);
            console.log($('.mytask')[this.attribute]);
            console.log(taskView.render().el);
            $('.mytask')[this.attribute].appendChild(taskView.render().el);
        },

        //add ui events
        events: {
            "keypress input.task": "updateAddTask",
        },
        
        updateAddTask: function(e){
            console.log('updateAddTask' + e.keyCode);
            console.log($($('.task')[this.attribute]).is(':focus'));
            if(e.keyCode == 13 && $($('.task')[this.attribute]).is(':focus') == true)
                this.addTask();
        },
        addTask: function(){
            console.log('Task added');
            var formData = {};
            console.log($('.addTask')[this.attribute]);
            var new_task = $('.addTask')[this.attribute];
            new_task = $(new_task);
            var task_text = $(new_task).find('input').val();
            $(new_task).find('input').val("");
            new_task = '' + task_text;
            
            
            this.collection.add({task: new_task});
        }

    });

        //create instance of master view
        function addNewList(){
                var width = $('.tasks').length * 360;
                console.log(width);
                console.log(screen.width);
                if(width + 500> screen.width){
                    $('#stage').css('overflow-x','scroll');
                    $('#main').width(width + 360);
                }
               $('#main').append($($('#listTemplate').html()));
               var attribute = $('.tasks').length - 1;
               var x = new TaskListView({}, {attribute: attribute,tasks: []});
               $($($('.tasks')[attribute]).find('input')[0]).bind('keypress', function(e){x.updateAddTask(e);});
               $('.mytask').sortable();
        }
