`View`

`LayoutInflater` 的 `inflate(int resource, ViewGroup root, boolean attachToRoot)` 方法

## 步骤

当我们调用 layoutInflater.inflate() 方法时，会经过一下阶段

1. 把对应的 xml 资源转成 XmlResourceParser（通过 AssetManager 的 openXmlBlockAsset() 方法，会调到原生的 AssetManager 对应的方法）
2. 前进到根节点
3. 先创建根节点的 View，记作 temp
4. 再递归地遍历子 View
5. 创建 View 的时候，会先调用 Factory 和 Factory2 进行拦截，这是系统提供的一个 Hook 方法（换肤框架可以在这里做文章）

## 关于 merge

- 当根节点是 merge 时，会直接递归地变量子 View

## 不同参数的情况

- 当传入了 parent 且 attachToRoot 为 true 时，会把生成的 View 依附到 parent，并返回 parent
- 当传入了 parent 但 attachToRoot 为 false 时，parent 只会作为一个约束的 LayoutParams，返回的是 child
- 当未传入 parent，attachToRoot 失效，且 LayoutParams 失效（因为没有 ViewGroup 约束），返回的是 child