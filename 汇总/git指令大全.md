# Git工作目录主要分为：
1.工作区  （git add ↓）
2.暂存区    (git commit  ↓)
3.对象库
![](https://upload-images.jianshu.io/upload_images/23325872-33b6ed73361e4120.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# Git文件状态
1.Git文件
    已被版本库管理的文件。
2.已修改
    在工作目录修改git文件
3.已暂存
    对已修改的文件执行Git暂存操作，将文件存入暂存区。
4.已提交
    将已暂存的文件执行Git操作，将文件存入版本库中。
![Git文件状态](https://upload-images.jianshu.io/upload_images/23325872-7b93659134304275.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# Git常用命令
 ### 1 git inint ：
初始化一个版本库。

### 2 git init --bare 
创建一个裸库，没有工作区。主要用来存放存放。

### 3 git add <file>
可以将一个未被追踪的文件（untracked）添加到版本库的暂存区当中（tracked）。

### 4 git status
查看版本库状态。

### 5 git rm --cached <file>
将文件从暂存区中移除。回退到已修改的状态。不会丢弃内容

### 6 git commit 或者 git commit -m "xxxxx"
将暂存区的文件添加到对象库中。

### 7 git commit --amend  -m 'xxxxxx'
替换掉上次提交commit的消息。

### 8 git config --global
配置全局信息。

### 9 git config --local 
针对于特定的项目。

### 10 git checkout -- <file>
丢弃掉相对于暂存区中最后一次添加的文件内容所做的变更。
丢弃工作目录当中的修改。（没有执行git add前的所有操作）

### 11  git rm <file>
从对象库删除git中的文件，同时也会删除本地文件。(必须是commit的)
1.删除了一个文件。
2.将被删除的文件纳入到暂存区。

### 12  git checkout -- <file>
撤销对工作区修改；这个命令是以最新的存储时间节点（add和commit）为参照，覆盖工作区对应文件file；这个命令改变的是工作区

### 13  git reset HEAD -- <file> 
清空add命令向暂存区提交的关于file文件的修改（Ustage）；这个命令仅改变暂存区，并不改变工作区，这意味着在无任何其他操作的情况下，工作区中的实际文件同该命令运行之前无任何改变

### 14 git mv <source file> <target file>
重命名或者将文件进行移动。
实际是分为两个步骤。创建一个新文件，并删除旧文件。

### 15 git log 或者 git log -n（显示最近n次更新）
查看提交历史。
![查看历史提交](https://upload-images.jianshu.io/upload_images/23325872-f24e74f1b10eaee4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 15.1  git log -p filename 
查看指定文件的提交历史

### 16 git branch 或者 git branch -a
查看当前版本库中所有的分支

### 17 git branch new_branch
在当前分支的基础上创建新分支。

### 18 git checkout branch
切换分支。

### 19 git checkout -b new_branch
创建新分支并切换到新分支上。

### 20 git checkout -b [local_branch_name] origin/new_branch
从远程分支获取到本地。origin/new_branch远程分支与远程服务器上的分支是相对应的。

### 21 git branhch -d branch_name
删除分支。（如果要删除的分支与当前分支的内容没有合并，不允许删除。）

### 22 git branch -D branch_name
强制删除分支。

### 23 git merge branch_name
将branch_name 分支上的修改合并到当前的分支中。



#### FastForward

![commit对象链](https://upload-images.jianshu.io/upload_images/23325872-cbcbc4e1e20221d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/23325872-ced3a7da3897ccff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
1.HEAD 指向的是当前的分支。
2.master（分支名）指向的是提交。


![创建dev分支](https://upload-images.jianshu.io/upload_images/23325872-2eac42fb6dec8478.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![修改dev分支](https://upload-images.jianshu.io/upload_images/23325872-edb46fed13e4f1c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![将dev分支合并master分支](https://upload-images.jianshu.io/upload_images/23325872-9d73929bcd04f4c9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 24 解决冲突
修改代码后，需要调用 git add <file> 标记为已经解决冲突。
再执行git commit 标识merge完成。

![分支合并](https://upload-images.jianshu.io/upload_images/23325872-338ae0d9db4b2175.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 25. git merge -- no-ff branch_name
合并分支不适用FastForward，这样会多出一个commitId。


### 26 git reset --hard HEAD^^ 
### 或者 git reset --hard HEAD~2
### 或者 git reset --hard commit_id.
回退到某个版本。

### 27 git reflog
查看git操作记录

### 28 git reset HEAD <file>
将之前添加到暂存区的内容从暂存区移除到工作区。

### 29 git branch -m old_branch_name new_branch_name
修改分支名称

### 30 git stash 或者 git stash save 'xxx'
将工作目录的记录进行临时保存。

### 31 git stash list
查看临时保存的列表。

### 32 git stash pop 
恢复临时保存的信息。并删除掉记录。

### 33  git stash apply
恢复临时保存的信息，但是并不删除记录。

### 34 git stash drop
删除临时保存的信息。

### 35 git tag tag_name
创建一个轻量级标签。

### 36  git tag -a tag_name -m 'xxxx'
创建一个带有附注的标签

### 37 git tag -d tag_name
删除标签。

### 38 git tag
查看所有标签。

### 39 git tag -l "v*"
查找标签。

### 40 git blame <file>
查看文件的修改记录。会显示每一行是谁修改的，还有修改时间和commit_id。

### 41 git diff
比较工作区和暂存区文件的区别。
暂存区视为原始文件，工作区视为目标文件。

### 42 git diff commit_id
比较工作区与具体对象库（版本库、git commit后的）的差别。
对象库视为原始文件，工作区视为目标文件。

### 43 gif diff HEAD
比较工作区与当前对象库（版本库、git commit后的）的差别。
版本库视为原始文件，工作区视为目标文件。

### 44 git diff --cached 或者 git diff --cached commit_id
比较暂存区和对象库（版本库、git commit后的）的差别。

### 45 git remote add origin xxxx.git
这里的origin为一个别名，实际代表xxxx.git。
 ### 46 git push -u origin master
将本地的仓库推送至远程的仓库，并与之进行关联。

### 47 git remote show origin
查看远程版本库信息。

### 48 git config --global alias.别名 要替代的命令
给指令起别名。

### 49 git push --set-upstream origin branch_name
1.将当前的本地分支推送到远程。
2.在远程也创建一个名字叫跟他同名的分支。
3.并将它们的关系绑定起来。远程分支为本地分支的上游分支。

### 50 gitt push--set-upstream origin develop/develop2
将本地分支develop推送至远程分支develop2。

### 51 git push origin :branch_name
删除远程分支。（将一个空分支推送到远程分支，冒号之前空格）

### 52 git push origin --delete branch_name
删除远程分支。

### 53 重命名远程分支
要想重命名远程分支。
1.需要先删除远程分支。git push origin --delete branch_name
2.然后将本地分支改名。git branch -m old_branch new_branch
3.再将本地分支推送至远程分支。git push --set-upstream origin branch_name

### 54 push 和 pull
push 操作的完整命令是：
git push origin srcBranch:destBranch
srcBranch（本地分支）
destBranch（远程分支）

pull操作完整命令是：
git pull origin srcBranch:destBranch
srcBranch（远程分支）
destBranch（本地分支）

HEAD标记符：HEAD文件是一个指向你当前所在分支的引用标识符。

### 55 git push origin tag_name
推送分支到远程。

### 56 git push origin --tag
把本地尚未推送到远程的标签都推送到远程。

### 57 git push origin --delete tag tag_name
删除远程标签。

### 58 git remote purne origin
将本地无效的分支进行删除。（如果远程分支已经被删除）

### 59 git log origin/master
       git log remotes/origin/master
       git log refs/remotes/origin/master
查看远程分支的提交记录。
前两种最终都会转换为第三种进行调用。

### 60 git submodule add A B
将远程分支A地址作为子仓库，然后在本地文件夹会创建B文件夹。并把A的源码clone到B文件夹中。

### 61 git submodule foreach git pull
遍历每个submodule进行pull操作

### 62 git submodule init
第一次获取工程的时候，需要在主工程执行submodule的初始化操作。注册

### 63 git submodule update --recursive
递归更新submodule

### 64 一次性获取获取主模块和子模块
git clone xxxx   --recursive

### 65 删除submodule
git rm --cache mymodule
rm -rf mymodule
git add commit -m 'remove'
git push
rm .gitmodule
git add .
git commit -m 'delete .gitsubmodule'
git push

### 66 通过subtree添加子工程
git remote add subtree-module xxxx.git（subtree-module为自定义的名称。主要就是将xxx.git赋值给它，方便后面书写）
git subtree add --prefix=subtree subtree-origin master
本地创建一个叫substree的目录，并从subtree-origin对应的地址拉取master的代码。

### 67 git cheey-pick commit_id
将commit_id对应的提交给应用到到当前分支。

### 68 git rebase branch_name（建议只在本地分支使用）
git rebase 与 merge的功能类似，但在git log可以看到有所区别。
实际上可以理解上，将一个分支上的修改应用到另一个分支上。

![rebase parent变化](https://upload-images.jianshu.io/upload_images/23325872-1ad9836e04593f18.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/23325872-19c523fbc2d04f24.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/23325872-8395b2a800d6c369.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/23325872-37e702846db0317a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 69 rebase 冲突处理
1.解决冲突后，使用git add进行添加标记。
2.执行git rebase --continue。
3.git rebase --skip 可以跳过这个冲突，并保留现有分支的。

### 70 git rebase --abort
种植rebase，分支会恢复到rebase开始之前的状态。